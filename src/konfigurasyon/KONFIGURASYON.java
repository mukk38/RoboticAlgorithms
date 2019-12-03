/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 0.9.5.3</a>, using an XML
 * Schema.
 * $Id$
 */

package konfigurasyon;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Comment describing your root element
 *  
 * 
 * @version $Revision$ $Date$
 */
public class KONFIGURASYON implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _yazilimGorunum
     */
    private konfigurasyon.YazilimGorunum _yazilimGorunum;


      //----------------/
     //- Constructors -/
    //----------------/

    public KONFIGURASYON() {
        super();
    } //-- konfigurasyon.KONFIGURASYON()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'yazilimGorunum'.
     * 
     * @return the value of field 'yazilimGorunum'.
     */
    public konfigurasyon.YazilimGorunum getYazilimGorunum()
    {
        return this._yazilimGorunum;
    } //-- konfigurasyon.YazilimGorunum getYazilimGorunum() 

    /**
     * Method isValid
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Sets the value of field 'yazilimGorunum'.
     * 
     * @param yazilimGorunum the value of field 'yazilimGorunum'.
     */
    public void setYazilimGorunum(konfigurasyon.YazilimGorunum yazilimGorunum)
    {
        this._yazilimGorunum = yazilimGorunum;
    } //-- void setYazilimGorunum(konfigurasyon.YazilimGorunum) 

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static java.lang.Object unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (konfigurasyon.KONFIGURASYON) Unmarshaller.unmarshal(konfigurasyon.KONFIGURASYON.class, reader);
    } //-- java.lang.Object unmarshal(java.io.Reader) 

    /**
     * Method validate
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
