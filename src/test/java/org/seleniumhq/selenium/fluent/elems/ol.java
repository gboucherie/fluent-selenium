package org.seleniumhq.selenium.fluent.elems;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.seleniumhq.selenium.fluent.BaseTest;
import org.seleniumhq.selenium.fluent.FluentExecutionStopped;
import org.seleniumhq.selenium.fluent.FluentWebDriverImpl;
import org.seleniumhq.selenium.fluent.FluentWebElements;
import org.seleniumhq.selenium.fluent.WebDriverJournal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class ol extends BaseTest {

    private StringBuilder sb;
    private WebDriver wd;
    private FluentWebDriverImpl fwd;

    @Before
    public void setup() {
        sb = new StringBuilder();
        wd = new WebDriverJournal(sb);
        fwd = new FluentWebDriverImpl(wd);
        FAIL_ON_NEXT.set(null);
    }

    @Test
    public void ol_functionality() {

        FluentWebElements fe = fwd.ol()
                .ol(By.xpath("@foo = 'bar'"))
                .ol(By.cssSelector("baz"))
                .ols();

        assertThat(fe, notNullValue());
        assertThat(sb.toString(), equalTo(
                "wd0.findElement(By.tagName: ol) -> we1\n" +
                        "we1.getTagName() -> 'ol'\n" +
                        "we1.findElement(By.xpath: .//ol[@foo = 'bar']) -> we2\n" +
                        "we2.getTagName() -> 'ol'\n" +
                        "we2.findElement(By.selector: baz) -> we3\n" +
                        "we3.getTagName() -> 'ol'\n" +
                        "we3.findElements(By.tagName: ol) -> [we4, we5]\n" +
                        "we4.getTagName() -> 'ol'\n" +
                        "we5.getTagName() -> 'ol'\n"
        ));
    }

    @Test
    public void ols_functionality() {
        FluentWebElements fe = fwd.ol()
                .ols(By.name("qux"));

        assertThat(fe, notNullValue());
        assertThat(sb.toString(), equalTo(
                "wd0.findElement(By.tagName: ol) -> we1\n" +
                        "we1.getTagName() -> 'ol'\n" +
                        "we1.findElements(By.name: qux) -> [we2, we3]\n" +
                        "we2.getTagName() -> 'ol'\n" +
                        "we3.getTagName() -> 'ol'\n"
        ));
    }

    @Test
    public void ol_mismatched() {
        try {
            fwd.ol(By.linkText("mismatching_tag_name"))
                    .clearField();
            fail("should have barfed");
        } catch (FluentExecutionStopped e) {
            assertTrue(e.getCause().getMessage().contains("tag was incorrect"));
        }

    }



}
