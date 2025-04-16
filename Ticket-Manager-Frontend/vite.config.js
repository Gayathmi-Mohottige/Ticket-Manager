import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    port: 5176,  // Change to any fixed port you want
    strictPort: true  // Ensures Vite does not switch ports
  }
})
