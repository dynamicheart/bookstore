/**
 * 
 */
package com.dynamicheart.bookstore.business.exception;

/**
 * @author Umesh A
 *
 */
public class ConversionException extends Exception
{


    private static final long serialVersionUID = 8159619282623176223L;

    public ConversionException(final String msg, final Throwable cause)
  {
      super(msg, cause);
  }
    public ConversionException(final String msg)
  {
      super(msg);
  }

    public ConversionException(Throwable t)
  {
      super(t);
  }
  
  

}
