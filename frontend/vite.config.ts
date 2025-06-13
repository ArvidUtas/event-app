import { defineConfig } from 'vite';

export default defineConfig({
  server: {
    // Enables clean URL routing for SPAs
    port: 5173,
    open: true,
    strictPort: true
  }
});
