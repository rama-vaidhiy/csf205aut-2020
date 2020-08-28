<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
     xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
     <xsl:template match="/">
     <html lang="en">
     <head>
          <meta charset="UTF-8"/>
          <title><xsl:value-of select="/schemeOfWork/@courseID"></xsl:value-of> Scheme of Work</title>
     </head>
     <body>
     <h1>
          <center>
          Scheme of Work for <xsl:value-of select="/schemeOfWork/@courseID"></xsl:value-of>
          </center>
     </h1>
          <center>
               <table border="solid">
                    <thead>
                         <tr>
                              <th>Course Title</th>
                              <th>Course Duration</th>
                              <th>Instructor</th>
                              <th>Course Dates</th>
                         </tr>
                    </thead>
                    <tbody>
                         <tr>
                              <td><xsl:value-of select="/schemeOfWork/courseDetails/courseTitle/text()"></xsl:value-of></td>
                              <td><xsl:value-of select="/schemeOfWork/courseDetails/courseDuration/text()"></xsl:value-of>&#160;
                              <xsl:value-of select="/schemeOfWork/courseDetails/courseDuration/@length"></xsl:value-of>
                              </td>
                              <td><xsl:value-of select="/schemeOfWork/courseDetails/courseInstructor/instructor/text()"></xsl:value-of>
                                   (<xsl:value-of select="/schemeOfWork/courseDetails/courseInstructor/instructor/@emailID"></xsl:value-of> )</td>
                              <td>
                                   <xsl:value-of select="/schemeOfWork/courseDetails/courseDates/startDate/text()"></xsl:value-of> to
                                   <xsl:value-of select="/schemeOfWork/courseDetails/courseDates/endDate/text()"></xsl:value-of>
                              </td>
                         </tr>
                    </tbody>
               </table>
          </center>
          <br/>
          <h2>Recommended Books</h2>
          <div>

               <ol>

                  <xsl:apply-templates select="/schemeOfWork/booksRecommended"></xsl:apply-templates>
               </ol>

          </div>

          <h2>Lecture Syllabus</h2>

          <div>
          <table border="solid">
               <thead>
                    <th>Week</th>
                    <th>Topic</th>
                    <th>Other Details</th>
                    <th>Labs</th>

               </thead>
               <tbody>
                    <xsl:apply-templates select="schemeOfWork/lectureSyllabus"></xsl:apply-templates>
               </tbody>
          </table>
          </div>
          <br/>
          <h2>Mid Term Topics</h2>
          <xsl:call-template name="examList"><xsl:with-param name="examName">midterm</xsl:with-param></xsl:call-template>
          <br/>
          <h2>End Term Topics</h2>
          <xsl:call-template name="examList"><xsl:with-param name="examName">endterm</xsl:with-param></xsl:call-template>

     </body>
     </html>

     </xsl:template>

     <xsl:template match="/schemeOfWork/booksRecommended">
               <xsl:for-each select="book">
                    <li><xsl:value-of select="./title/text()"></xsl:value-of> by
                    <xsl:for-each select="./authors/author">
                         <xsl:value-of select="text()"></xsl:value-of> &#160;

                    </xsl:for-each>
                    </li>
               </xsl:for-each>

     </xsl:template>
     <xsl:template match="/schemeOfWork/lectureSyllabus" >
          <xsl:for-each select="week">
               <tr>
                    <td><xsl:value-of select="@id"></xsl:value-of></td>
                    <td><xsl:value-of select="./topics/text()"></xsl:value-of></td>
               <xsl:if test="not(./otherDetails)">
                    <td></td>
               </xsl:if>
               <xsl:if test="./otherDetails">
                    <td><xsl:value-of select="./otherDetails/text()"></xsl:value-of></td>
               </xsl:if>
                    <td><xsl:value-of select="./lab/text()"></xsl:value-of></td>
               </tr>
          </xsl:for-each>


     </xsl:template>
     <xsl:template name="examList">
          <xsl:param name="examName">midterm</xsl:param>
          <xsl:apply-templates select="/schemeOfWork/lectureSyllabus/week[@test = $examName]"></xsl:apply-templates>
     </xsl:template>

     <xsl:template match="/schemeOfWork/lectureSyllabus/week" >
          Week <xsl:value-of select="@id"></xsl:value-of> &#160; <xsl:value-of select="./topics/text()"></xsl:value-of><br/>
     </xsl:template>
</xsl:stylesheet>