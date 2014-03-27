/**
 *                    BioJava development code
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  If you do not have a copy,
 * see:
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Copyright for this code is held jointly by the individual
 * authors.  These should be listed in @author doc comments.
 *
 * For more information on the BioJava project and its aims,
 * or to join the biojava-l mailing list, visit the home page
 * at:
 *
 *      http://www.biojava.org/
 *
 * Created on Aug 31, 2011
 * Created by Andreas Prlic
 *
 * @since 3.0.2
 */
package org.biojava.bio.structure.xtal.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.TreeMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.biojava.bio.structure.xtal.SpaceGroup;



@XmlRootElement(name = "TreeSetSpaceGroupWrapper", namespace ="http://www.biojava.org")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class TreeMapSpaceGroupWrapper implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4193799052494327416L;
	TreeMap<Integer,SpaceGroup> data;
	
	static JAXBContext jaxbContext;
	static {
		try {
			jaxbContext= JAXBContext.newInstance(TreeMapSpaceGroupWrapper.class);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public TreeMapSpaceGroupWrapper(){
		data = new TreeMap<Integer,SpaceGroup>();
	}

	public TreeMap<Integer,SpaceGroup> getData() {
		return data;
	}

	public void setData(TreeMap<Integer,SpaceGroup> data) {
		this.data = data;
	}
	public  String toXML(){

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		PrintStream ps = new PrintStream(baos);

		try {

			Marshaller m = jaxbContext.createMarshaller();

			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			m.marshal( this, ps);
			

		} catch (Exception e){
			e.printStackTrace();
		}

		return baos.toString();

	}

	public static TreeMapSpaceGroupWrapper fromXML(String xml){

		TreeMapSpaceGroupWrapper job = null;

		try {

			Unmarshaller un = jaxbContext.createUnmarshaller();

			ByteArrayInputStream bais = new ByteArrayInputStream(xml.getBytes());

			job = (TreeMapSpaceGroupWrapper) un.unmarshal(bais);

		} catch (Exception e){
			e.printStackTrace();
		}

		return job;
	}
	
	
}
