<<<<<<< HEAD
import { registerPlugin } from '@capacitor/core';

import type { SimpleExoPlayerPlugin } from './definitions';

const SimpleExoPlayer = registerPlugin<SimpleExoPlayerPlugin>('SimpleExoPlayer', {
  web: () => import('./web').then(m => new m.SimpleExoPlayerWeb()),
});

export * from './definitions';
export { SimpleExoPlayer };
=======
export * from './definitions';
>>>>>>> 1a1eda2bacf6623cee60ffd2185b2c48bbd9b56c
