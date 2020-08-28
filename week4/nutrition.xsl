<xsl:stylesheet version="2.0"
                xmlns:rcp="http://www.brics.dk/ixwt/recipes"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="rcp:collection">
    <nutrition>
      <xsl:apply-templates select="rcp:recipe"/>
    </nutrition>
  </xsl:template>

  <xsl:template match="rcp:recipe">
    <dish name="{rcp:title/text()}"
          calories="{rcp:nutrition/@calories}"
          fat="{rcp:nutrition/@fat}"
          carbohydrates="{rcp:nutrition/@carbohydrates}"
          protein="{rcp:nutrition/@protein}"
          alcohol="{if (rcp:nutrition/@alcohol) 
                    then rcp:nutrition/@alcohol else '0%'}"/>
  </xsl:template>

</xsl:stylesheet>
