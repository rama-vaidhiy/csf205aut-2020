<xsl:stylesheet version="2.0"
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
     <xsl:import href="grades.xsl"/>

     <xsl:template match="students">
       <summary>
         <xsl:apply-imports/>
       </summary>
     </xsl:template>

  </xsl:stylesheet>
