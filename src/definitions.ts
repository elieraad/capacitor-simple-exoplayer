export interface SimpleExoPlayerPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
}
