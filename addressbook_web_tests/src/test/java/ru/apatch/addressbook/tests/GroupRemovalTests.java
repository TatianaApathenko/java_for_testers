package ru.apatch.addressbook.tests;

import ru.apatch.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;


public class GroupRemovalTests extends TestBase {

    @Test
    public void CanRemoveGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
       var newGroups = app.groups().getList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);

    }

    @Test
    void canRemoveAllGroupsAtOnce() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();;
        Assertions.assertEquals(0, app.groups().getCount());
        }
    }