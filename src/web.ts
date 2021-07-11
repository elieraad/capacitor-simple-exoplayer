import { WebPlugin } from '@capacitor/core';

import type { SimpleExoPlayerPlugin } from './definitions';

export class SimpleExoPlayerWeb extends WebPlugin implements SimpleExoPlayerPlugin {
  load(options: { src: string; }): void {
    console.log('ECHO', options);
    throw new Error('Method not implemented.');
  }
  play(): void {
    throw new Error('Method not implemented.');
  }
  pause(): void {
    throw new Error('Method not implemented.');
  }
  seek(options?: { value: number; }): Promise<number> {
    console.log('ECHO', options);
    throw new Error('Method not implemented.');
  }
  duration(): Promise<number> {
    throw new Error('Method not implemented.');
  }
  release(): void {
    throw new Error('Method not implemented.');
  }
}
