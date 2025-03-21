<template>
  <div id="container">
    <p>Not registered yet? <RouterLink to="/register">Register new user</RouterLink></p>
    <h1>Login</h1>
    <form @submit.prevent="login">
      <div>
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="username" required />
      </div>
      <div>
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required />
      </div>

      <button type="submit">Login</button>
    </form>
    <p v-if="message" :class="messageClass">{{ message }}</p>
  </div>
</template>

<script setup>
import {computed, ref} from 'vue'
import axios from 'axios'

const username = ref('')
const password = ref('')
const message = ref('')
const isError = ref(false)

const messageClass = computed(() => {
  return isError.value ? 'error' : 'success'
})

const login = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/auth/login', {
      username: username.value,
      password: password.value
    })

    const token = response.data.token
    console.log(token)
    sessionStorage.setItem('jwtToken', token)
    message.value = 'Login successful'
    isError.value = false;
  } catch (error) {
    console.error('Login failed:', error)
    message.value = 'Login failed: ' + (error.response?.data?.error || error.message)
    isError.value = true;
  }
}
</script>

<style scoped>
#container {
  max-width: 24.5rem;
  margin: 0 auto;
  padding: 2rem;
  border: 1px solid var(--color-border);
  border-radius: 4px;
  background-color: var(--color-background-soft);
}

form div {
  margin-bottom: 1rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
  color: var(--color-heading);
}

input {
  width: 100%;
  padding: 0.5rem;
  box-sizing: border-box;
  border: 1px solid var(--color-border);
  border-radius: 4px;
  background-color: var(--color-background);
  color: var(--color-text);
}

button {
  width: 100%;
  padding: 0.5rem;
  background-color: hsla(160, 100%, 37%, 1);
  color: var(--vt-c-white);
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.4s;
}

button:hover {
  background-color: hsla(160, 100%, 37%, 0.8);
}

.success {
  background-color: hsla(120, 100%, 75%, 0.6);
  color: var(--color-text);
  margin-top: 1rem;
  padding: 0.5rem;
  border-radius: 4px;
}

.error {
  background-color: hsla(0, 100%, 75%, 0.6);
  color: var(--color-text);
  margin-top: 1rem;
  padding: 0.5rem;
  border-radius: 4px;
}
</style>
