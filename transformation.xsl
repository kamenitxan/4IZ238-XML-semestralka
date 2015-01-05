<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<xsl:for-each select="pozadavek">
			<tr>
				<td><xsl:value-of select="person/name"/></td>
				<td><xsl:value-of select="person/department"/></td>
				<td><xsl:value-of select="person/place"/></td>
				<td><xsl:value-of select="person/phone"/></td>
				<td><xsl:value-of select="problem/restart"/></td>
				<td><xsl:value-of select="problem/type"/></td>
				<td><xsl:value-of select="problem/desc"/></td>
				<td><xsl:value-of select="pc/osVersion"/></td>
			</tr>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>