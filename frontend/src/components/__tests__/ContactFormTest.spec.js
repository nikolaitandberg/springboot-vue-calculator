import { describe, it, expect, beforeEach } from 'vitest'
import { shallowMount } from '@vue/test-utils'
import { createPinia, setActivePinia } from 'pinia'
import SimpleContactForm from '../SimpleContactForm.vue'
import { TextElement, ButtonElement, Vueform } from '@vueform/vueform'

describe('SimpleContactForm', () => {
  beforeEach(() => {
    setActivePinia(createPinia())
  })

  it('renders properly', () => {
    const wrapper = shallowMount(SimpleContactForm, {
      global: {
        plugins: [createPinia()],
        components: {
          TextElement,
          ButtonElement,
          Vueform,
        },
      },
    })
    expect(wrapper.exists()).toBe(true)
  })
})
