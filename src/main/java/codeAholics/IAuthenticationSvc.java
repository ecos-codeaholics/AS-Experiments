/** Copyright or License
 *
 */

package codeAholics;
/**
 * Package: codeAholics
 *
 * Class: IAuthenticationSvc IAuthenticationSvc.java
 * 
 * Original Author: @author AOSORIO
 * 
 * Description: [one line class summary]
 * 
 * Implementation: [Notes on implementation]
 *
 * Created: Aug 2, 2016 1:40:28 PM
 * 
 */
public interface IAuthenticationSvc {
	
	public boolean doAuthentication(String p1, String p2);
	public String getAnswer();
}
