export interface SimpleExoPlayerPlugin {
  load(options: {src: string}): void;
  play(): void;
  pause(): void;
  seek(options?: {value: number}): Promise<number>;
  duration(): Promise<number>;
  release(): void;
}
