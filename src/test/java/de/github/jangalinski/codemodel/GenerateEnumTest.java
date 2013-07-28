package de.github.jangalinski.codemodel;

import static com.sun.codemodel.JExpr._this;
import static com.sun.codemodel.JExpr.lit;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.After;
import org.junit.Test;

import com.google.common.io.Files;
import com.sun.codemodel.ClassType;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JEnumConstant;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;

public class GenerateEnumTest {

    private static final int PRIVATE_FINAL = JMod.PRIVATE + JMod.FINAL;
    private final JCodeModel codeModel = new JCodeModel();
    private final File tempDir = Files.createTempDir();

    @Test
    public void shouldGenerateEnumWithConstructor() throws Exception {
        JDefinedClass definedClass = codeModel.rootPackage()._class(JMod.PUBLIC, "ReportColumns", ClassType.ENUM);
        
        JFieldVar field1 = definedClass.field(PRIVATE_FINAL, String.class, "column");
        JFieldVar field2 = definedClass.field(PRIVATE_FINAL, Boolean.class, "filterable");
        JFieldVar field3 = definedClass.field(PRIVATE_FINAL, Boolean.class, "includeInHavingClause");
        
        JMethod constructor = definedClass.constructor(JMod.PRIVATE);
        JVar param1 = constructor.param(String.class, "column");
        JVar param2 = constructor.param(Boolean.class, "filterable");
        JVar param3 = constructor.param(Boolean.class, "includeInHavingClause");

        JBlock body = constructor.body();
        body.assign(_this().ref(field1), param1);
        body.assign(_this().ref(field2), param2);
        body.assign(_this().ref(field3), param3);
        
        JEnumConstant enumMonth = definedClass.enumConstant("MONTH");
        enumMonth.arg(lit("month"));
        enumMonth.arg(lit(true));
        enumMonth.arg(lit(false));

        JEnumConstant enumDay = definedClass.enumConstant("DAY");
        enumDay.arg(lit("day"));
        enumDay.arg(lit(false));
        enumDay.arg(lit(true));

        codeModel.build(tempDir);
        printGeneratedClass();
    }

    private void printGeneratedClass() throws IOException {
        System.out.println(tempDir.getAbsolutePath());
        System.out.println(Files.toString(tempDir.listFiles()[0], Charset.defaultCharset()));
    }

    @After
    public void cleanUp() {
        tempDir.listFiles()[0].delete();
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
