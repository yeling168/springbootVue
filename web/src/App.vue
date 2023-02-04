<template>
  <a-layout id="components-layout-demo-top-side-2">
    <the-header></the-header>
    <router-view/>
  </a-layout>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import TheHeader from '@/components/the-header.vue';

export default defineComponent({
  name: 'app',
  components: {
    TheHeader,
  }
})
</script>

<style>
</style>
