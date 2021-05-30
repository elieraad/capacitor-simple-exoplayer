import { registerPlugin } from '@capacitor/core';

import type { SimpleExoPlayerPlugin } from './definitions';

const SimpleExoPlayer = registerPlugin<SimpleExoPlayerPlugin>('SimpleExoPlayer', {
  web: () => import('./web').then(m => new m.SimpleExoPlayerWeb()),
});

export * from './definitions';
export { SimpleExoPlayer };
