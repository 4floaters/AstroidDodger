/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameLoop;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author Jesse
 */

public class ResourceManager 
{    
   
    
    public <T extends Resource> T load(Class<T> c,String Path) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException
    {
       T t = (T)c.getDeclaredConstructor(String.class).newInstance(Path);
       return t;
    }
    
}