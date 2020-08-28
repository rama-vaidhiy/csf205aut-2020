<xsl:stylesheet version="2.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:b="http://businesscard.org"
                xmlns="http://www.w3.org/1999/xhtml">

  <xsl:template match="b:card">
    <html>
      <head>
        <title><xsl:value-of select="b:name/text()"/></title>
      </head>
      <body bgcolor="#ffffff">
        <table border="3">
          <tr>
            <td>
              <xsl:apply-templates select="b:name"/><br/>
              <xsl:apply-templates select="b:title"/><p/>
              <tt><xsl:apply-templates select="b:email"/></tt><br/> 
              <xsl:if test="b:phone">
                Phone: <xsl:apply-templates select="b:phone"/><br/>
              </xsl:if>
            </td>
            <td>
              <xsl:if test="b:logo">
                <img src="{b:logo/@uri}"/>
              </xsl:if>
            </td>
          </tr>
        </table>
      </body>
    </html>
  </xsl:template> 

  <xsl:template match="b:name|b:title|b:email|b:phone">
    <xsl:value-of select="text()"/>
  </xsl:template>

</xsl:stylesheet>
