<template>
  <div class="form-container">
    <Vueform v-model="data" sync validate-on="" v-slot="{ invalid }" @success="handleSuccess">
      <TextElement name="name" label="name:" :rules="['required']" />
      <TextElement name="email" label="e-mail:" :rules="['required', 'email']" />
      <TextElement name="msg" label="message:" :rules="['required']" />
      <ButtonElement name="submit" button-label="Submit" submits :disabled="invalid" />
    </Vueform>
  </div>
</template>

<script setup>
import { computed } from 'vue'
import { useContactStore } from '@/stores/contact'

defineOptions({
  name: 'SimpleContactForm',
})

const store = useContactStore()

const data = computed({
  get: () => store.registration,
  set: (data) => (store.registration = data),
})

const handleSuccess = (response, form$) => {
  console.log(response)
  console.log(response.status)
  console.log(response.data)

  form$.messageBag.clear()
  form$.messageBag.append('Form submitted', 'message')
  form$.reset()
}
</script>

<style>
.form-container {
  width: 30rem;
}
</style>
