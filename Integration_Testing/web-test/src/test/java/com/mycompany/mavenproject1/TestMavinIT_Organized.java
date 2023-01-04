/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/SeleniumTests/SeleneseIT.java to edit this template
 */
package com.mycompany.mavenproject1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.*;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;


public class TestMavinIT_Organized {
    
    public TestMavinIT_Organized(){
    
    }
    

    @org.testng.annotations.BeforeTest
    public static void setUpClass() throws Exception {
        //initialize driver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize(); //to maximize browser window
    }

    @org.testng.annotations.AfterTest
    public static void tearDownClass() throws Exception {

      //Close the browser
        Thread.sleep(2000);
        driver.quit();
    }

   
    static WebDriver driver;
    
    @DataProvider(name = "getData")
    public Object[][] getData(){
         Object [][] loginData = new Object [1][2];

         loginData[0][0]="Admin";
         loginData[0][1]="admin123";

         return loginData;

    }

    @Test(dataProvider = "getData")
    public void testLogin(String userName, String password) throws Exception {
        
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        Thread.sleep(1000);
        driver.findElement(By.name("username")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.className("orangehrm-login-button")).click();
        Thread.sleep(3000);
        //Assert.assertTrue(driver.findElement(By.className("oxd-userdropdown-name")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.className("oxd-userdropdown-name")).getText(),"dolly singh");
    }
    
    /**
     * @throws Exception
     */
    @Test
    public void screenShots() throws Exception{
            TakesScreenshot screenshot= (TakesScreenshot) driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);
            File destination = new File(System.getProperty("user.dir") + "/screenshots/FailedImage.png");
            FileHandler.copy(source, destination);  
    }

    @Test 
    public void testSideBar() throws Exception {
        
        Thread.sleep(1000);
        driver.findElement(By.className("oxd-icon-button")).click();
        Thread.sleep(3000);
        driver.findElement(By.className("oxd-icon-button")).click();
        Thread.sleep(3000);
        //Assert.assertTrue(driver.findElement(By.className("oxd-userdropdown-name")).isDisplayed());
    }
    
    @Test 
    public void testTabAdmin() throws Exception {
        driver.findElement(By.cssSelector(".oxd-main-menu-item[href='/web/index.php/admin/viewAdminModule']")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("oxd-checkbox-input-icon")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("oxd-checkbox-input-icon")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.className("oxd-topbar-header-breadcrumb-level")).getText(),"User Management");
         
    }
    
    
    @Test 
    public void testTabPim() throws Exception {
        driver.findElement(By.cssSelector(".oxd-main-menu-item[href='/web/index.php/pim/viewPimModule']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.className("oxd-table-filter-header-title")).getText(),"Employee Information");
    }


    @Test 
    public void testTabLeave() throws Exception {
        driver.findElement(By.cssSelector(".oxd-main-menu-item[href='/web/index.php/leave/viewLeaveModule']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.className("oxd-table-filter-header-title")).getText(),"Leave List");
    }

    @Test 
    public void testTabTime() throws Exception {
        driver.findElement(By.cssSelector(".oxd-main-menu-item[href='/web/index.php/time/viewTimeModule']")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("oxd-table-cell-action-space")).click();
        Thread.sleep(3000);
        driver.findElement(By.className("oxd-button--ghost")).click();
        Thread.sleep(3000);
   
        driver.findElement(By.className("oxd-button--ghost")).click();
        Thread.sleep(3000);

        driver.findElement(By.className("oxd-topbar-body-nav-tab")).click();
        Thread.sleep(2000);
        driver.findElement(By.className("oxd-topbar-body-nav-tab-link")).click();
        Thread.sleep(3000);

        driver.findElement(By.className("orangehrm-timeperiod-icon")).click();
        Thread.sleep(4000);
        driver.findElement(By.className("--next")).click();
        Thread.sleep(4000);
        
        driver.findElement(By.className("oxd-button--ghost")).click();
        Thread.sleep(3000);

        driver.findElement(By.className("oxd-button--medium")).click();
        Thread.sleep(3000);
        
        Assert.assertEquals(driver.findElement(By.className("oxd-topbar-header-breadcrumb-module")).getText(),"Time");
    }

    @Test 
    public void testTabRecruitment() throws Exception {
        driver.findElement(By.cssSelector(".oxd-main-menu-item[href='/web/index.php/recruitment/viewRecruitmentModule']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.className("oxd-table-filter-header-title")).getText(),"Candidates");
    }

    @Test 
    public void testTabInfo() throws Exception {
        driver.findElement(By.cssSelector(".oxd-main-menu-item[href='/web/index.php/pim/viewMyDetails']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.className("orangehrm-tabs-item")).getText(),"Personal Details");
    }

    @Test 
    public void testTabPerformance() throws Exception {
        driver.findElement(By.cssSelector(".oxd-main-menu-item[href='/web/index.php/performance/viewPerformanceModule']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.className("oxd-table-filter-header-title")).getText(),"Employee Reviews");
    }


    @Test 
    public void testTabDashboard() throws Exception {
        driver.findElement(By.cssSelector(".oxd-main-menu-item[href='/web/index.php/dashboard/index']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.className("orangehrm-dashboard-widget-name")).getText(),"Time at Work");
    }

    @Test 
    public void testTabDirectory() throws Exception {
        driver.findElement(By.cssSelector(".oxd-main-menu-item[href='/web/index.php/directory/viewDirectory']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.className("oxd-table-filter-header-title")).getText(),"Directory");
    }

    @Test 
    public void testTabMaintenance() throws Exception {
        driver.findElement(By.cssSelector(".oxd-main-menu-item[href='/web/index.php/maintenance/viewMaintenanceModule']")).click();
        Thread.sleep(2000);
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector(".orangehrm-admin-access-button")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.className("oxd-userdropdown-name")).isDisplayed());
//      Assert.assertEquals(driver.findElement(By.className("oxd-topbar-header-breadcrumb-module")).getText(),"Maintenance");
    }

    @Test 
    public void testTabBuzz() throws Exception {
        driver.findElement(By.cssSelector(".oxd-main-menu-item[href='/web/index.php/buzz/viewBuzz']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.className("orangehrm-module-under-development-heading")).getText(),"Launching Soon");
    }

    @Test 
    public void testLogout() throws Exception {
        driver.findElement(By.className("oxd-userdropdown-tab")).click();
        Thread.sleep(1000);
        driver.findElement(By.cssSelector(".oxd-userdropdown-link[href='/web/index.php/auth/logout']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.className("orangehrm-login-title")).getText(),"Login");
    }
 
}
