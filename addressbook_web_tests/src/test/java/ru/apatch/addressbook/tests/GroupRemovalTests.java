package ru.apatch.addressbook.tests;

import com.google.common.collect.RangeMap;
import io.qameta.allure.Allure;
import ru.apatch.addressbook.model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupRemovalTests extends TestBase {


    @Test
    public void CanRemoveGroup() {
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getGroupCount() == 0){
                app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
            }
        });
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Allure.step("Validating results", step -> {
            Assertions.assertEquals(newGroups, expectedList);
        });
    }

    @Test
    public void CanRemoveAllGroupsAtOnce(){
        if (app.hbm().getGroupCount() == 0){
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0, app.hbm().getGroupCount());
    }
}