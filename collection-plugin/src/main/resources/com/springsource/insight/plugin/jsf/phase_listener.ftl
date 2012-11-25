<#ftl strip_whitespace=true>
<#import "/insight-1.0.ftl" as insight />

<@insight.group label="Phase Listener Details">
    <@insight.entry name="Method" value=operation.method />
    <@insight.entry name="Phase Id" value=operation.phaseId />
    
    <@insight.entry name="Exception" value=operation.exception />
</@insight.group>