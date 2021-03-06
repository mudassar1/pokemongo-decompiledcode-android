package com.google.android.gms.internal;

import java.util.concurrent.Future;

@zzgr
public abstract class zzhz
  implements zzgh<Future>
{
  private volatile Thread zzIl;
  private boolean zzIm;
  private final Runnable zzx = new Runnable()
  {
    public final void run()
    {
      zzhz.zza(zzhz.this, Thread.currentThread());
      zzhz.this.zzbn();
    }
  };
  
  public zzhz()
  {
    this.zzIm = false;
  }
  
  public zzhz(boolean paramBoolean)
  {
    this.zzIm = paramBoolean;
  }
  
  public final void cancel()
  {
    onStop();
    if (this.zzIl != null) {
      this.zzIl.interrupt();
    }
  }
  
  public abstract void onStop();
  
  public abstract void zzbn();
  
  public final Future zzgz()
  {
    if (this.zzIm) {}
    for (zziq localzziq = zzic.zza(1, this.zzx);; localzziq = zzic.zza(this.zzx)) {
      return localzziq;
    }
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/internal/zzhz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */