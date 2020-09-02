package com.want.webservice.ZRFCSD026;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>DT_REQ_ZRFCSD026 complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DT_REQ_ZRFCSD026"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="IT_VBAK" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="BSTNK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="VKORG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="VTWEG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="SPART" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="KUNNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="KUNNR1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="PRSDT" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                   &lt;element name="MEMO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="BSTDK" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *                   &lt;element name="AUART" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="AUGRU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="KOSTL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="IT_VBAP" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="BSTNK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="POSNR1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="LGORT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="PRICE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="UNIT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="KWMENG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="CMPRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="MEMO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="PSTYV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="TMPID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="TMPTEXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="OT_VBAK" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="BSTNK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="TEXT200" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="VBELN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="OT_VBAP" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="BSTNK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="POSNR1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="POSNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="TEXT200" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_REQ_ZRFCSD026", propOrder = {
    "itvbak",
    "itvbap",
    "otvbak",
    "otvbap"
})
public class DTREQZRFCSD026 {

    @XmlElement(name = "IT_VBAK")
    protected List<DTREQZRFCSD026 .ITVBAK> itvbak;
    @XmlElement(name = "IT_VBAP")
    protected List<DTREQZRFCSD026 .ITVBAP> itvbap;
    @XmlElement(name = "OT_VBAK")
    protected List<DTREQZRFCSD026 .OTVBAK> otvbak;
    @XmlElement(name = "OT_VBAP")
    protected List<DTREQZRFCSD026 .OTVBAP> otvbap;

    /**
     * Gets the value of the itvbak property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itvbak property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getITVBAK().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTREQZRFCSD026 .ITVBAK }
     * 
     * 
     */
    public List<DTREQZRFCSD026 .ITVBAK> getITVBAK() {
        if (itvbak == null) {
            itvbak = new ArrayList<DTREQZRFCSD026 .ITVBAK>();
        }
        return this.itvbak;
    }

    /**
     * Gets the value of the itvbap property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itvbap property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getITVBAP().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTREQZRFCSD026 .ITVBAP }
     * 
     * 
     */
    public List<DTREQZRFCSD026 .ITVBAP> getITVBAP() {
        if (itvbap == null) {
            itvbap = new ArrayList<DTREQZRFCSD026 .ITVBAP>();
        }
        return this.itvbap;
    }

    /**
     * Gets the value of the otvbak property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otvbak property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOTVBAK().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTREQZRFCSD026 .OTVBAK }
     * 
     * 
     */
    public List<DTREQZRFCSD026 .OTVBAK> getOTVBAK() {
        if (otvbak == null) {
            otvbak = new ArrayList<DTREQZRFCSD026 .OTVBAK>();
        }
        return this.otvbak;
    }

    /**
     * Gets the value of the otvbap property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otvbap property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOTVBAP().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DTREQZRFCSD026 .OTVBAP }
     * 
     * 
     */
    public List<DTREQZRFCSD026 .OTVBAP> getOTVBAP() {
        if (otvbap == null) {
            otvbap = new ArrayList<DTREQZRFCSD026 .OTVBAP>();
        }
        return this.otvbap;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="BSTNK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="VKORG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="VTWEG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="SPART" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="KUNNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="KUNNR1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="PRSDT" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *         &lt;element name="MEMO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="BSTDK" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
     *         &lt;element name="AUART" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="AUGRU" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="KOSTL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "bstnk",
        "vkorg",
        "vtweg",
        "spart",
        "kunnr",
        "kunnr1",
        "prsdt",
        "memo",
        "bstdk",
        "auart",
        "augru",
        "kostl"
    })
    public static class ITVBAK {

        @XmlElement(name = "BSTNK")
        protected String bstnk;
        @XmlElement(name = "VKORG")
        protected String vkorg;
        @XmlElement(name = "VTWEG")
        protected String vtweg;
        @XmlElement(name = "SPART")
        protected String spart;
        @XmlElement(name = "KUNNR")
        protected String kunnr;
        @XmlElement(name = "KUNNR1")
        protected String kunnr1;
        @XmlElement(name = "PRSDT")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar prsdt;
        @XmlElement(name = "MEMO")
        protected String memo;
        @XmlElement(name = "BSTDK")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar bstdk;
        @XmlElement(name = "AUART")
        protected String auart;
        @XmlElement(name = "AUGRU")
        protected String augru;
        @XmlElement(name = "KOSTL")
        protected String kostl;

        /**
         * 获取bstnk属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBSTNK() {
            return bstnk;
        }

        /**
         * 设置bstnk属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBSTNK(String value) {
            this.bstnk = value;
        }

        /**
         * 获取vkorg属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVKORG() {
            return vkorg;
        }

        /**
         * 设置vkorg属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVKORG(String value) {
            this.vkorg = value;
        }

        /**
         * 获取vtweg属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVTWEG() {
            return vtweg;
        }

        /**
         * 设置vtweg属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVTWEG(String value) {
            this.vtweg = value;
        }

        /**
         * 获取spart属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSPART() {
            return spart;
        }

        /**
         * 设置spart属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSPART(String value) {
            this.spart = value;
        }

        /**
         * 获取kunnr属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKUNNR() {
            return kunnr;
        }

        /**
         * 设置kunnr属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKUNNR(String value) {
            this.kunnr = value;
        }

        /**
         * 获取kunnr1属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKUNNR1() {
            return kunnr1;
        }

        /**
         * 设置kunnr1属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKUNNR1(String value) {
            this.kunnr1 = value;
        }

        /**
         * 获取prsdt属性的值。
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getPRSDT() {
            return prsdt;
        }

        /**
         * 设置prsdt属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setPRSDT(XMLGregorianCalendar value) {
            this.prsdt = value;
        }

        /**
         * 获取memo属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMEMO() {
            return memo;
        }

        /**
         * 设置memo属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMEMO(String value) {
            this.memo = value;
        }

        /**
         * 获取bstdk属性的值。
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getBSTDK() {
            return bstdk;
        }

        /**
         * 设置bstdk属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setBSTDK(XMLGregorianCalendar value) {
            this.bstdk = value;
        }

        /**
         * 获取auart属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAUART() {
            return auart;
        }

        /**
         * 设置auart属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAUART(String value) {
            this.auart = value;
        }

        /**
         * 获取augru属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAUGRU() {
            return augru;
        }

        /**
         * 设置augru属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAUGRU(String value) {
            this.augru = value;
        }

        /**
         * 获取kostl属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKOSTL() {
            return kostl;
        }

        /**
         * 设置kostl属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKOSTL(String value) {
            this.kostl = value;
        }

    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="BSTNK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="POSNR1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="MATNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="LGORT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="PRICE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="UNIT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="KWMENG" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="CMPRE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="MEMO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="PSTYV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="TMPID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="TMPTEXT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "bstnk",
        "posnr1",
        "matnr",
        "lgort",
        "price",
        "unit",
        "kwmeng",
        "cmpre",
        "memo",
        "pstyv",
        "tmpid",
        "tmptext"
    })
    public static class ITVBAP {

        @XmlElement(name = "BSTNK")
        protected String bstnk;
        @XmlElement(name = "POSNR1")
        protected String posnr1;
        @XmlElement(name = "MATNR")
        protected String matnr;
        @XmlElement(name = "LGORT")
        protected String lgort;
        @XmlElement(name = "PRICE")
        protected String price;
        @XmlElement(name = "UNIT")
        protected String unit;
        @XmlElement(name = "KWMENG")
        protected String kwmeng;
        @XmlElement(name = "CMPRE")
        protected String cmpre;
        @XmlElement(name = "MEMO")
        protected String memo;
        @XmlElement(name = "PSTYV")
        protected String pstyv;
        @XmlElement(name = "TMPID")
        protected String tmpid;
        @XmlElement(name = "TMPTEXT")
        protected String tmptext;

        /**
         * 获取bstnk属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBSTNK() {
            return bstnk;
        }

        /**
         * 设置bstnk属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBSTNK(String value) {
            this.bstnk = value;
        }

        /**
         * 获取posnr1属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPOSNR1() {
            return posnr1;
        }

        /**
         * 设置posnr1属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPOSNR1(String value) {
            this.posnr1 = value;
        }

        /**
         * 获取matnr属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMATNR() {
            return matnr;
        }

        /**
         * 设置matnr属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMATNR(String value) {
            this.matnr = value;
        }

        /**
         * 获取lgort属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLGORT() {
            return lgort;
        }

        /**
         * 设置lgort属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLGORT(String value) {
            this.lgort = value;
        }

        /**
         * 获取price属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPRICE() {
            return price;
        }

        /**
         * 设置price属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPRICE(String value) {
            this.price = value;
        }

        /**
         * 获取unit属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUNIT() {
            return unit;
        }

        /**
         * 设置unit属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUNIT(String value) {
            this.unit = value;
        }

        /**
         * 获取kwmeng属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getKWMENG() {
            return kwmeng;
        }

        /**
         * 设置kwmeng属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setKWMENG(String value) {
            this.kwmeng = value;
        }

        /**
         * 获取cmpre属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCMPRE() {
            return cmpre;
        }

        /**
         * 设置cmpre属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCMPRE(String value) {
            this.cmpre = value;
        }

        /**
         * 获取memo属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMEMO() {
            return memo;
        }

        /**
         * 设置memo属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMEMO(String value) {
            this.memo = value;
        }

        /**
         * 获取pstyv属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPSTYV() {
            return pstyv;
        }

        /**
         * 设置pstyv属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPSTYV(String value) {
            this.pstyv = value;
        }

        /**
         * 获取tmpid属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTMPID() {
            return tmpid;
        }

        /**
         * 设置tmpid属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTMPID(String value) {
            this.tmpid = value;
        }

        /**
         * 获取tmptext属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTMPTEXT() {
            return tmptext;
        }

        /**
         * 设置tmptext属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTMPTEXT(String value) {
            this.tmptext = value;
        }

    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="BSTNK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="TEXT200" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="VBELN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "bstnk",
        "text200",
        "vbeln"
    })
    public static class OTVBAK {

        @XmlElement(name = "BSTNK")
        protected String bstnk;
        @XmlElement(name = "TEXT200")
        protected String text200;
        @XmlElement(name = "VBELN")
        protected String vbeln;

        /**
         * 获取bstnk属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBSTNK() {
            return bstnk;
        }

        /**
         * 设置bstnk属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBSTNK(String value) {
            this.bstnk = value;
        }

        /**
         * 获取text200属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTEXT200() {
            return text200;
        }

        /**
         * 设置text200属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTEXT200(String value) {
            this.text200 = value;
        }

        /**
         * 获取vbeln属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getVBELN() {
            return vbeln;
        }

        /**
         * 设置vbeln属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setVBELN(String value) {
            this.vbeln = value;
        }

    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="BSTNK" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="POSNR1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="POSNR" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="TEXT200" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "bstnk",
        "posnr1",
        "posnr",
        "text200"
    })
    public static class OTVBAP {

        @XmlElement(name = "BSTNK")
        protected String bstnk;
        @XmlElement(name = "POSNR1")
        protected String posnr1;
        @XmlElement(name = "POSNR")
        protected String posnr;
        @XmlElement(name = "TEXT200")
        protected String text200;

        /**
         * 获取bstnk属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBSTNK() {
            return bstnk;
        }

        /**
         * 设置bstnk属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBSTNK(String value) {
            this.bstnk = value;
        }

        /**
         * 获取posnr1属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPOSNR1() {
            return posnr1;
        }

        /**
         * 设置posnr1属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPOSNR1(String value) {
            this.posnr1 = value;
        }

        /**
         * 获取posnr属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPOSNR() {
            return posnr;
        }

        /**
         * 设置posnr属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPOSNR(String value) {
            this.posnr = value;
        }

        /**
         * 获取text200属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTEXT200() {
            return text200;
        }

        /**
         * 设置text200属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTEXT200(String value) {
            this.text200 = value;
        }

    }

}
