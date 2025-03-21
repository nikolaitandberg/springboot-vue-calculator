import { describe, it, expect } from 'vitest'
import { mount } from '@vue/test-utils'
import SimpleCalculator from '../SimpleCalculator.vue'

describe('SimpleCalculator', () => {
  it('renders properly', () => {
    const wrapper = mount(SimpleCalculator)
    expect(wrapper.exists()).toBe(true)
  })

  it('adds numbers correctly', async () => {
    const wrapper = mount(SimpleCalculator)
    await wrapper.find('[data-testid="1"]').trigger('click')
    await wrapper.find('[data-testid="add"]').trigger('click')
    await wrapper.find('[data-testid="2"]').trigger('click')
    await wrapper.find('[data-testid="equals"]').trigger('click')
    expect(wrapper.find('.display p').text()).toBe('3')
  })

  it('subtracts numbers correctly', async () => {
    const wrapper = mount(SimpleCalculator)
    await wrapper.find('[data-testid="9"]').trigger('click')
    await wrapper.find('[data-testid="subtract"]').trigger('click')
    await wrapper.find('[data-testid="7"]').trigger('click')
    await wrapper.find('[data-testid="equals"]').trigger('click')
    expect(wrapper.find('.display p').text()).toBe('2')
  })

  it('handles division by zero', async () => {
    const wrapper = mount(SimpleCalculator)
    await wrapper.find('[data-testid="1"]').trigger('click')
    await wrapper.find('[data-testid="divide"]').trigger('click')
    await wrapper.find('[data-testid="0"]').trigger('click')
    await wrapper.find('[data-testid="equals"]').trigger('click')
    expect(wrapper.find('.display p').text()).toBe('Error')
  })
})
