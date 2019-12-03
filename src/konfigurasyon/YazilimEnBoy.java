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
 * Class YazilimEnBoy.
 * 
 * @version $Revision$ $Date$
 */
public class YazilimEnBoy implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _en
     */
    private int _en;

    /**
     * keeps track of state for field: _en
     */
    private boolean _has_en;

    /**
     * Field _boy
     */
    private int _boy;

    /**
     * keeps track of state for field: _boy
     */
    private boolean _has_boy;


      //----------------/
     //- Constructors -/
    //----------------/

    public YazilimEnBoy() {
        super();
    } //-- konfigurasyon.YazilimEnBoy()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteBoy
     */
    public void deleteBoy()
    {
        this._has_boy= false;
    } //-- void deleteBoy() 

    /**
     * Method deleteEn
     */
    public void deleteEn()
    {
        this._has_en= false;
    } //-- void deleteEn() 

    /**
     * Returns the value of field 'boy'.
     * 
     * @return the value of field 'boy'.
     */
    public int getBoy()
    {
        return this._boy;
    } //-- int getBoy() 

    /**
     * Returns the value of field 'en'.
     * 
     * @return the value of field 'en'.
     */
    public int getEn()
    {
        return this._en;
    } //-- int getEn() 

    /**
     * Method hasBoy
     */
    public boolean hasBoy()
    {
        return this._has_boy;
    } //-- boolean hasBoy() 

    /**
     * Method hasEn
     */
    public boolean hasEn()
    {
        return this._has_en;
    } //-- boolean hasEn() 

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
     * Sets the value of field 'boy'.
     * 
     * @param boy the value of field 'boy'.
     */
    public void setBoy(int boy)
    {
        this._boy = boy;
        this._has_boy = true;
    } //-- void setBoy(int) 

    /**
     * Sets the value of field 'en'.
     * 
     * @param en the value of field 'en'.
     */
    public void setEn(int en)
    {
        this._en = en;
        this._has_en = true;
    } //-- void setEn(int) 

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static java.lang.Object unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (konfigurasyon.YazilimEnBoy) Unmarshaller.unmarshal(konfigurasyon.YazilimEnBoy.class, reader);
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
