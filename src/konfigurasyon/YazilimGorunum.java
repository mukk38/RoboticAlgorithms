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
import java.util.Enumeration;
import java.util.Vector;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class YazilimGorunum.
 * 
 * @version $Revision$ $Date$
 */
public class YazilimGorunum implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _yazilimEnBoyList
     */
    private java.util.Vector _yazilimEnBoyList;

    /**
     * Field _yazilimGridList
     */
    private java.util.Vector _yazilimGridList;


      //----------------/
     //- Constructors -/
    //----------------/

    public YazilimGorunum() {
        super();
        _yazilimEnBoyList = new Vector();
        _yazilimGridList = new Vector();
    } //-- konfigurasyon.YazilimGorunum()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addYazilimEnBoy
     * 
     * @param vYazilimEnBoy
     */
    public void addYazilimEnBoy(konfigurasyon.YazilimEnBoy vYazilimEnBoy)
        throws java.lang.IndexOutOfBoundsException
    {
        _yazilimEnBoyList.addElement(vYazilimEnBoy);
    } //-- void addYazilimEnBoy(konfigurasyon.YazilimEnBoy) 

    /**
     * Method addYazilimEnBoy
     * 
     * @param index
     * @param vYazilimEnBoy
     */
    public void addYazilimEnBoy(int index, konfigurasyon.YazilimEnBoy vYazilimEnBoy)
        throws java.lang.IndexOutOfBoundsException
    {
        _yazilimEnBoyList.insertElementAt(vYazilimEnBoy, index);
    } //-- void addYazilimEnBoy(int, konfigurasyon.YazilimEnBoy) 

    /**
     * Method addYazilimGrid
     * 
     * @param vYazilimGrid
     */
    public void addYazilimGrid(konfigurasyon.YazilimGrid vYazilimGrid)
        throws java.lang.IndexOutOfBoundsException
    {
        _yazilimGridList.addElement(vYazilimGrid);
    } //-- void addYazilimGrid(konfigurasyon.YazilimGrid) 

    /**
     * Method addYazilimGrid
     * 
     * @param index
     * @param vYazilimGrid
     */
    public void addYazilimGrid(int index, konfigurasyon.YazilimGrid vYazilimGrid)
        throws java.lang.IndexOutOfBoundsException
    {
        _yazilimGridList.insertElementAt(vYazilimGrid, index);
    } //-- void addYazilimGrid(int, konfigurasyon.YazilimGrid) 

    /**
     * Method enumerateYazilimEnBoy
     */
    public java.util.Enumeration enumerateYazilimEnBoy()
    {
        return _yazilimEnBoyList.elements();
    } //-- java.util.Enumeration enumerateYazilimEnBoy() 

    /**
     * Method enumerateYazilimGrid
     */
    public java.util.Enumeration enumerateYazilimGrid()
    {
        return _yazilimGridList.elements();
    } //-- java.util.Enumeration enumerateYazilimGrid() 

    /**
     * Method getYazilimEnBoy
     * 
     * @param index
     */
    public konfigurasyon.YazilimEnBoy getYazilimEnBoy(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _yazilimEnBoyList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (konfigurasyon.YazilimEnBoy) _yazilimEnBoyList.elementAt(index);
    } //-- konfigurasyon.YazilimEnBoy getYazilimEnBoy(int) 

    /**
     * Method getYazilimEnBoy
     */
    public konfigurasyon.YazilimEnBoy[] getYazilimEnBoy()
    {
        int size = _yazilimEnBoyList.size();
        konfigurasyon.YazilimEnBoy[] mArray = new konfigurasyon.YazilimEnBoy[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (konfigurasyon.YazilimEnBoy) _yazilimEnBoyList.elementAt(index);
        }
        return mArray;
    } //-- konfigurasyon.YazilimEnBoy[] getYazilimEnBoy() 

    /**
     * Method getYazilimEnBoyCount
     */
    public int getYazilimEnBoyCount()
    {
        return _yazilimEnBoyList.size();
    } //-- int getYazilimEnBoyCount() 

    /**
     * Method getYazilimGrid
     * 
     * @param index
     */
    public konfigurasyon.YazilimGrid getYazilimGrid(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _yazilimGridList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (konfigurasyon.YazilimGrid) _yazilimGridList.elementAt(index);
    } //-- konfigurasyon.YazilimGrid getYazilimGrid(int) 

    /**
     * Method getYazilimGrid
     */
    public konfigurasyon.YazilimGrid[] getYazilimGrid()
    {
        int size = _yazilimGridList.size();
        konfigurasyon.YazilimGrid[] mArray = new konfigurasyon.YazilimGrid[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (konfigurasyon.YazilimGrid) _yazilimGridList.elementAt(index);
        }
        return mArray;
    } //-- konfigurasyon.YazilimGrid[] getYazilimGrid() 

    /**
     * Method getYazilimGridCount
     */
    public int getYazilimGridCount()
    {
        return _yazilimGridList.size();
    } //-- int getYazilimGridCount() 

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
     * Method removeAllYazilimEnBoy
     */
    public void removeAllYazilimEnBoy()
    {
        _yazilimEnBoyList.removeAllElements();
    } //-- void removeAllYazilimEnBoy() 

    /**
     * Method removeAllYazilimGrid
     */
    public void removeAllYazilimGrid()
    {
        _yazilimGridList.removeAllElements();
    } //-- void removeAllYazilimGrid() 

    /**
     * Method removeYazilimEnBoy
     * 
     * @param index
     */
    public konfigurasyon.YazilimEnBoy removeYazilimEnBoy(int index)
    {
        java.lang.Object obj = _yazilimEnBoyList.elementAt(index);
        _yazilimEnBoyList.removeElementAt(index);
        return (konfigurasyon.YazilimEnBoy) obj;
    } //-- konfigurasyon.YazilimEnBoy removeYazilimEnBoy(int) 

    /**
     * Method removeYazilimGrid
     * 
     * @param index
     */
    public konfigurasyon.YazilimGrid removeYazilimGrid(int index)
    {
        java.lang.Object obj = _yazilimGridList.elementAt(index);
        _yazilimGridList.removeElementAt(index);
        return (konfigurasyon.YazilimGrid) obj;
    } //-- konfigurasyon.YazilimGrid removeYazilimGrid(int) 

    /**
     * Method setYazilimEnBoy
     * 
     * @param index
     * @param vYazilimEnBoy
     */
    public void setYazilimEnBoy(int index, konfigurasyon.YazilimEnBoy vYazilimEnBoy)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _yazilimEnBoyList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _yazilimEnBoyList.setElementAt(vYazilimEnBoy, index);
    } //-- void setYazilimEnBoy(int, konfigurasyon.YazilimEnBoy) 

    /**
     * Method setYazilimEnBoy
     * 
     * @param yazilimEnBoyArray
     */
    public void setYazilimEnBoy(konfigurasyon.YazilimEnBoy[] yazilimEnBoyArray)
    {
        //-- copy array
        _yazilimEnBoyList.removeAllElements();
        for (int i = 0; i < yazilimEnBoyArray.length; i++) {
            _yazilimEnBoyList.addElement(yazilimEnBoyArray[i]);
        }
    } //-- void setYazilimEnBoy(konfigurasyon.YazilimEnBoy) 

    /**
     * Method setYazilimGrid
     * 
     * @param index
     * @param vYazilimGrid
     */
    public void setYazilimGrid(int index, konfigurasyon.YazilimGrid vYazilimGrid)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index > _yazilimGridList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _yazilimGridList.setElementAt(vYazilimGrid, index);
    } //-- void setYazilimGrid(int, konfigurasyon.YazilimGrid) 

    /**
     * Method setYazilimGrid
     * 
     * @param yazilimGridArray
     */
    public void setYazilimGrid(konfigurasyon.YazilimGrid[] yazilimGridArray)
    {
        //-- copy array
        _yazilimGridList.removeAllElements();
        for (int i = 0; i < yazilimGridArray.length; i++) {
            _yazilimGridList.addElement(yazilimGridArray[i]);
        }
    } //-- void setYazilimGrid(konfigurasyon.YazilimGrid) 

    /**
     * Method unmarshal
     * 
     * @param reader
     */
    public static java.lang.Object unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (konfigurasyon.YazilimGorunum) Unmarshaller.unmarshal(konfigurasyon.YazilimGorunum.class, reader);
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
