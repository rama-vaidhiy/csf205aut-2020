<xsl:stylesheet version="2.0"
                xmlns="http://www.w3.org/1999/xhtml"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="nutrition">
    <html>
      <head>
        <title>Nutrition Table</title>
      </head>
      <body>
        <table border="1">
          <tr>
            <th>Dish</th>
            <th>Calories</th>
            <th>Fat</th>
            <th>Carbohydrates</th>
            <th>Protein</th>
          </tr>
          <xsl:apply-templates select="dish"/>
        </table>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="dish">
    <tr>
      <td><xsl:value-of select="@name"/></td>
      <td align="right"><xsl:value-of select="@calories"/></td>
      <td align="right"><xsl:value-of select="@fat"/></td>
      <td align="right"><xsl:value-of select="@carbohydrates"/></td>
      <td align="right"><xsl:value-of select="@protein"/></td>
    </tr>
  </xsl:template>

</xsl:stylesheet>
