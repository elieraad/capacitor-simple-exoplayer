import { WebPlugin } from '@capacitor/core';

import type { SimpleExoPlayerPlugin } from './definitions';

export class SimpleExoPlayerWeb extends WebPlugin implements SimpleExoPlayerPlugin {
  async echo(options: { value: string }): Promise<{ value: string }> {
    console.log('ECHO', options);
    return options;
  }
}
