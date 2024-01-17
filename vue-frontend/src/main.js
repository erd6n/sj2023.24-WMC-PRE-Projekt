import './assets/base.css'

import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from "axios";             // npm install axios

const app = createApp(App)

app.use(router)

app.mount('#app')
