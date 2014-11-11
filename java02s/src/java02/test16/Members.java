/*
Value Object
=> Class 문법을 사용하여 사용자 정의 데이터 타입 만들기

1) Serializable 인터페이스 구현
    => SerialVersionUID 스태틱 변수 선언

2) 인스턴스 변수 선언    

3) setter/getter 생성

4) 기본 생성자와 파라미터 값을 받는 생성자 선언

5) equals()/hashCode() 메서드 오버라이딩

6) toString() 오버라이딩
 */
package java02.test16;

import java.io.Serializable;

public class Members implements Serializable, Cloneable {
  private static final long serialVersionUID = 1L;

  protected String     id;
  protected String     email;
  protected String     uname;
  protected String     tel;
  protected String       pwd;
  protected String     fax;
  protected String     detAddr;
  protected String     phot;
  protected int        ano;

  public Members() {}


/*  public Members(String id, String email, String uname, String tel) {
    super();
    this.id = id;
    this.email = email;
    this.uname = uname;
    this.tel = tel;
  }*/



  public Members(String id, String email, String uname, String tel, String pwd,
      String fax, String detAddr, String phot, int ano) {
    this.id = id;
    this.email = email;
    this.uname = uname;
    this.tel = tel;
    this.pwd = pwd;
    this.fax = fax;
    this.detAddr = detAddr;
    this.phot = phot;
    this.ano = ano;
  }


  @Override
  public Members clone() throws CloneNotSupportedException {
    return (Members)super.clone();
  }



  @Override
  public String toString() {
    return "Members [id=" + id + ", email=" + email + ", uname=" + uname
        + ", tel=" + tel + ", pwd=" + pwd + ", fax=" + fax + ", detAddr="
        + detAddr + ", phot=" + phot + ", ano=" + ano + "]";
  }


  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ano;
    result = prime * result + ((detAddr == null) ? 0 : detAddr.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((fax == null) ? 0 : fax.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((phot == null) ? 0 : phot.hashCode());
    result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
    result = prime * result + ((tel == null) ? 0 : tel.hashCode());
    result = prime * result + ((uname == null) ? 0 : uname.hashCode());
    return result;
  }


  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Members other = (Members) obj;
    if (ano != other.ano)
      return false;
    if (detAddr == null) {
      if (other.detAddr != null)
        return false;
    } else if (!detAddr.equals(other.detAddr))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (fax == null) {
      if (other.fax != null)
        return false;
    } else if (!fax.equals(other.fax))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (phot == null) {
      if (other.phot != null)
        return false;
    } else if (!phot.equals(other.phot))
      return false;
    if (pwd != other.pwd)
      return false;
    if (tel == null) {
      if (other.tel != null)
        return false;
    } else if (!tel.equals(other.tel))
      return false;
    if (uname == null) {
      if (other.uname != null)
        return false;
    } else if (!uname.equals(other.uname))
      return false;
    return true;
  }


  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


  public String getEmail() {
    return email;
  }


  public void setEmail(String email) {
    this.email = email;
  }


  public String getUname() {
    return uname;
  }


  public void setUname(String uname) {
    this.uname = uname;
  }


  public String getTel() {
    return tel;
  }


  public void setTel(String tel) {
    this.tel = tel;
  }


  public String getPwd() {
    return pwd;
  }


  public void setPwd(String pwd) {
    this.pwd = pwd;
  }


  public String getFax() {
    return fax;
  }


  public void setFax(String fax) {
    this.fax = fax;
  }


  public String getDetAddr() {
    return detAddr;
  }


  public void setDetAddr(String detAddr) {
    this.detAddr = detAddr;
  }


  public String getPhot() {
    return phot;
  }


  public void setPhot(String phot) {
    this.phot = phot;
  }


  public int getAno() {
    return ano;
  }


  public void setAno(int ano) {
    this.ano = ano;
  }








}
