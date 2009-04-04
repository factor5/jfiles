package proj.subs;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Unit tests for subtitle modifier class.
 * 
 * @author Svilen Velikov
 *
 * 01.03.2009
 */
@Test
public class SubsModifierTest {

    private SubsModifier subMod;
    
    @BeforeTest
    public void init() {
	subMod = new SubsModifier();
    }
    
    @Test
    public void findCurrentDirectoryTest() throws IOException {
	String currentDir = "C:\\dev\\workspaces\\itpms\\SubsMod";
	Assert.assertEquals(subMod.findCurrentDirectory(), currentDir);
    }
    
    public void findSRTFilesNumberTest() throws IOException {
	int expectedNumber = 2;
	String curDir = subMod.findCurrentDirectory();
	System.out.println(curDir);
	File curFile = new File(curDir);
	subMod.findSRTFiles(curFile);
	Assert.assertEquals(subMod.pathList.size(), expectedNumber);
    }
    
}
