/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLoop;

import java.io.File;

/**
 *
 * @author Jesses
 */
public class Resource 
{    
    protected File file;
    
    public Resource(String Path)
    {
        file = new File(Path);
    }
    
}
