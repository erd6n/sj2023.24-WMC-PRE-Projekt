<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div id="main">
    <button type="button" class="myBtn" @click="$router.push('/')">Audits</button>
    <button type="button" class="myBtn" @click="$router.push('/law')">Laws</button>
    <button button type="button" class="myBtn" @click="$router.push('/questions')">Questions</button>
    
    <router-view v-slot=" { Component }">
      <transition name="fade" mode="out-in">
        <Component :is="Component" />
      </transition>
    </router-view>

  </div>    
</template>

<style scoped>
button {
  margin: 10px;
  text-decoration: none;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 0.3s;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>

