/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.map.utils;

import java.io.FileInputStream;
import java.util.Properties;

/**
 *
 * @author apandey
 */
public class AppProperties {

    private static final Properties prop = new Properties();

    static {
        try {
            FileInputStream propStream = new FileInputStream( "resources/app.properties" );
            prop.load( propStream );
        }
        catch ( Throwable ex ) {
            throw new ExceptionInInitializerError( ex );
        }
    }

    public static String getValue( String key ) {
        return prop.get( key ).toString().trim();
    }
}
