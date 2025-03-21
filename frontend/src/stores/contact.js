import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

// standard eksempel
export const useCounterStore = defineStore('counter', () => {
  const count = ref(0)
  const doubleCount = computed(() => count.value * 2)
  function increment() {
    count.value++
  }

  return { count, doubleCount, increment }
})

export const useContactStore = defineStore('contact', {
  state: () => {
    return {
      registration: {},
    }
  },
})
