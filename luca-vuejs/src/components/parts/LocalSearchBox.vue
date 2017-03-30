<template>
  <form id="content-search" name="content-search" action="[search-action]" method="post">
    <fieldset>
      <div class="input-group margin-bottom-none">
        <input type="text" name="first" id="content-searchbox" class="input-group-field" v-bind:value="value"  v-on:input="updateValue($event.target.value)">
        <div class="input-group-button">
          <input type="submit" name="submit" class="button icon icon-functional">
        </div>
      </div>
    </fieldset>
  </form>
</template>

<script>
export default {
  props: ['value'],
  methods: {
    updateValue (value) {
      this.$emit('input', value)
    }
  }
}
</script>
