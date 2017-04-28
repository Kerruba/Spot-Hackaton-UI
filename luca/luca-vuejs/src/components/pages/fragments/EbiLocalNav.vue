<template>
<div class="masthead row">
    <!-- local-title -->
    <div class="columns medium-7" id="local-title">
        <h1><a href="[service-url]" title="Back to [service-name] homepage">EBI Framework</a></h1>
    </div>
    <!-- /local-title -->
    <!-- local-nav -->
    <main-nav></main-nav>
    <!-- /local-nav -->
</div>
</template>

<script>
    import NavLinks from '@/components/pages/fragments/NavLinks'
    export default {
      components: {
        'main-nav': NavLinks
      }
    }
</script>
