/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kittyMiningBuddyLibrary.interfaces;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Roy
 */
public interface IXMLPersistable<T> {
    
    Boolean writeXMLToOutputStream(OutputStream exOutStream);
    
    T readObjectFromInputStream(InputStream exInStream);    
}
