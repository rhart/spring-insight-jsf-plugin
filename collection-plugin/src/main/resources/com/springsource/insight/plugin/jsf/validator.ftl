<#ftl strip_whitespace=true>
<#import "/insight-1.0.ftl" as insight />

<@insight.group label="Validator Details">
    <@insight.entry name="UI Component Id" value=operation.uiComponentId />
    <@insight.entry name="Value To Validate" value=operation.value />
    <@insight.entry name="Validator Class" value=operation.validatorClass />
    <@insight.entry name="Validator Method" value=operation.validatorClassMethod />
</@insight.group>