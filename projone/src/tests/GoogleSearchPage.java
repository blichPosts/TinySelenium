package tests;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/*
<input id="lst-ib" class="gsfi" maxlength="2048" name="q" autocomplete="off" title="Search" value="" aria-label="Search" aria-haspopup="false" 
role="combobox" aria-autocomplete="list" style="border: medium none; padding: 0px; margin: 0px; height: auto; width: 100%;
 background: transparent url("data:image/gif;base64,R0lGODlhAQABAID/AMDAwAAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw%3D%3D") repeat scroll 0% 0%; 
 position: absolute; z-index: 6; left: 0px; outline: medium none;" dir="ltr" spellcheck="false" type="text"/>
*/

public class GoogleSearchPage extends BaseSelenium
{
    // Here's the element
    //@FindBy(how = How.ID_OR_NAME, using="q")  
    //@FindBy(how = How.ID_OR_NAME, using="lst-ib")
    
	// @FindBy(how = How.CSS, using="#lst-ib")
	@FindBy(how = How.CSS, using=".gLFyf.gsfi")	
	
	//.gLFyf.gsfi
    private WebElement ele;
	
	@FindBy(how = How.CSS, using=".FPdoLc.VlcLAe>center>input:nth-of-type(1)")
    private WebElement eleClick;
	
    public void searchFor(String text) 
    {
        // And here we use it. Note that it looks like we've
        // not properly instantiated it yet....
        ele.sendKeys(text);
        //ele.submit();
        // System.out.println(q.getTagName());
    }	
    
    public void ClickGoogleSearch() throws InterruptedException 
    {
    	// Thread.sleep(1000);
    	// driver.findElement(By.xpath("//div[@class='FPdoLc VlcLAe']//input[@value='Google Search']")).click();
    	eleClick.click();
        Assert.assertEquals(7, 8);
    }	
    
	
}
