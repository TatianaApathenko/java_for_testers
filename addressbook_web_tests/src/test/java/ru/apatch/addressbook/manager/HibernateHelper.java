package ru.apatch.addressbook.manager;


import io.qameta.allure.Step;
import ru.apatch.addressbook.manager.hbm.ContactRecord;
import ru.apatch.addressbook.manager.hbm.GroupRecord;
import ru.apatch.addressbook.model.ContactData;
import ru.apatch.addressbook.model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {
    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                .addAnnotatedClass(GroupRecord.class)
                .addAnnotatedClass(ContactRecord.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<GroupData> convertList(List<GroupRecord> records){
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)){
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    @Step
    public List<GroupData> getGroupList(){
        return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    @Step
    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convert(groupData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convertContact).collect(Collectors.toList());
    }

    private static ContactData convertContact(ContactRecord record) {
        return new ContactData().withId(""+ record.id)
                .withName(record.firstname)
                .withLastName(record.lastname)
                .withAddress(record.address)
                .withMobile(record.mobile)
                .withEmail(record.email)
                .withHome(record.home)
                .withWork(record.work)
                .withSecondary(record.phone2);
    }

    private static ContactRecord convertContact(ContactData data) {
        var id = data.id();
        if("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.firstname(), data.lastname(), data.address(), data.mobile(), data.email());
    }


    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void CreateContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertContact(contactData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }

    public List<GroupData> getGroupsByContact(ContactData contactData) {
        return sessionFactory.fromSession(session -> {
            return convertList(session.get(ContactRecord.class, contactData.id()).groups);
        });
    }

    public List<ContactData> getContactsNotInGroup() {
        var allContacts = getContactList();
        allContacts.removeIf(contactData -> {
            var groups = getGroupsByContact(contactData);
            return (groups != null) && (!groups.isEmpty());
        });
        return allContacts;
    }

    public String getIdContactByName(String firstname) {
        return sessionFactory.fromSession(session -> {
            return session.createQuery(String.format("select id from ContactRecord where firstname='%s'",
                            firstname),
                    Integer.class).getSingleResult().toString();
        });
    }
}