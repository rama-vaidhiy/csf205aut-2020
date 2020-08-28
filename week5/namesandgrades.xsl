<xsl:stylesheet version="2.0"
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="students">
    <summary>
       <xsl:result-document href="names.html">
      <xsl:apply-templates mode="names" select="student"/>
    </xsl:result-document>
       <xsl:result-document href="grades.html">
      <xsl:apply-templates mode="grades" select="student"/>
    </xsl:result-document>
    </summary>
  </xsl:template>
  <xsl:template mode="names" match="student">
    <name>
      <xsl:attribute name="id" select="@id"/>
      <xsl:value-of select="name"/>
    </name>
  </xsl:template>
  <xsl:template mode="grades" match="student">
      <grades>
        <xsl:attribute name="id" select="@id"/>
        <xsl:apply-templates select=".//@grade"/>
      </grades>
    </xsl:template>
    <xsl:template match="@grade">
      <grade>
        <xsl:value-of select="."/>
      </grade>
    </xsl:template>
  </xsl:stylesheet>
