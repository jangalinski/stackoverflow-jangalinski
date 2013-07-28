package de.github.jangalinski.codemodel;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Test;

import com.google.common.collect.FluentIterable;
import com.google.common.io.Files;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;

public class GenerateEnumTest {

    private final JCodeModel codeModel = new JCodeModel();
    private final File tempDir = Files.createTempDir();

    @Test
    public void shouldGenerateEnumWithConstructor() throws Exception {
        JDefinedClass definedClass = codeModel._class("ReportColumns");
        codeModel.build(tempDir);
        printGeneratedClass();
    }

    private void printGeneratedClass() throws IOException {
        System.out.println(Files.readLines(tempDir.listFiles()[0], Charset.defaultCharset()));
    }

    @After
    public void cleanUp() {
        tempDir.delete();
    }

    // public enum REPORT_COLUMNS {
    //
    // MONTH("month", true, false),
    // DAY("day", false, true);
    //
    // private final String column;
    // private final boolean filterable;
    // private final boolean includeInHavingClause;
    //
    // private REPORT_COLUMNS(String column, boolean filterable, boolean
    // includeInHavingClause) {
    // this.column = column;
    // this.filterable = filterable;
    // this.includeInHavingClause = includeInHavingClause;
    // }
    //
    // public String getColumn() {
    // return column;
    // }
    //
    // public boolean isFilterable() {
    // return filterable;
    // }
    //
    // public boolean includeInHavingClause() {
    // return includeInHavingClause;
    // }
    // }
}
