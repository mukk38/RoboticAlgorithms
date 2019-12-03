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
 * Class YazilimGrid.
 * 
 * @version $Revision$ $Date$
 */
public class YazilimGrid implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _xekseniElemanSayisi
     */
    private int _xekseniElemanSayisi;

    /**
     * keeps track of state for field: _xekseniElemanSayisi
     */
    private boolean _has_xekseniElemanSayisi;

    /**
     * Field _yekseniElemanSayisi
     */
    private int _yekseniElemanSayisi;

    /**
     * keeps track of state for field: _yekseniElemanSayisi
     */
    private boolean _has_yekseniElemanSayisi;


      //----------------/
     //- Constructors -/
    //----------------/

    public YazilimGrid() {
        super();
    } //-- konfigurasyon.YazilimGrid()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method deleteXekseniElemanSayisi
     */
    public void deleteXekseniElemanSayisi()
    {
        this._has_xekseniElemanSayisi= false;
    } //-- void deleteXekseniElemanSayisi() 

    /**
     * Method deleteYekseniElemanSayisi
     */
    public void deleteYekseniElemanSayisi()
    {
        this._has_yekseniElemanSayisi= false;
    } //-- void deleteYekseniElemanSayisi() 

    /**
     * Returns the value of field 'xekseniElemanSayisi'.
     * 
     * @return the value of field 'xekseniElemanSayisi'.
     */
    public int getXekseniElemanSayisi()
    {
        return this._xekseniElemanSayisi;
    } //-- int getXekseniElemanSayisi() 

    /**
     * Returns the value of field 'yekseniElemanSayisi'.
     * 
     * @return the value of field 'yekseniElemanSayisi'.
     */
    public int getYekseniElemanSayisi()
    {
        return this._yekseniElemanSayisi;
    } //-- int getYekseniElemanSayisi() 

    /**
     * Method hasXekseniElemanSayisi
     */
    public boolean hasXekseniElemanSayisi()
    {
        return this._has_xekseniElemanSayisi;
    } //-- boolean hasXekseniElemanSayisi() 

    /**
     * Method hasYekseniElemanSayisi
     */
    public boolean hasYekseniElemanSayisi()
    {
        return this._has_yekseniElemanSayisi;
    } //-- boolean hasYekseniElemanSayisi() 

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
     * Sets the value of field 'xekseniElemanSayisi'.
     * 
     * @param xekseniElemanSayisi the value of field
     * 'xekseniElemanSayisi'.
     */
    public void setXekseniElemanSayisi(int xekseniElemanSayisi)
    {
        this._xekseniElemanSayisi = xekseniElemanSayisi;
        this._has_xekseniElemanSayisi = true;
    } //-- void setXekseniElemanSayisi(int) 

    /**
     * Sets the value of field 'yekseniElemanSayisi'.
     * 
     * @param yekseniElemanSayisi the value of field
     * 'yekseniElemanSayisi'.
     */
    public void setYekseniElemanSayisi(int yekseniElemanSayisi)
    {
        this._yekseniElemanSayisi = yekseniElemanSayisi;
        this._has_yekseniElemanSayisi = true;
    } //-- void setYekseniElemanSayisi(int) 

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static java.lang.Object unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (konfigurasyon.YazilimGrid) Unmarshaller.unmarshal(konfigurasyon.YazilimGrid.class, reader);
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
