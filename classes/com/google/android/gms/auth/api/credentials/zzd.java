package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zza.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzd
  implements Parcelable.Creator<IdToken>
{
  static void zza(IdToken paramIdToken, Parcel paramParcel, int paramInt)
  {
    int i = zzb.zzaq(paramParcel);
    zzb.zza(paramParcel, 1, paramIdToken.getAccountType(), false);
    zzb.zzc(paramParcel, 1000, paramIdToken.mVersionCode);
    zzb.zza(paramParcel, 2, paramIdToken.getIdToken(), false);
    zzb.zzI(paramParcel, i);
  }
  
  public IdToken zzH(Parcel paramParcel)
  {
    String str1 = null;
    int i = zza.zzap(paramParcel);
    int j = 0;
    String str2 = null;
    while (paramParcel.dataPosition() < i)
    {
      int k = zza.zzao(paramParcel);
      switch (zza.zzbM(k))
      {
      default: 
        zza.zzb(paramParcel, k);
        break;
      case 1: 
        str2 = zza.zzp(paramParcel, k);
        break;
      case 1000: 
        j = zza.zzg(paramParcel, k);
        break;
      case 2: 
        str1 = zza.zzp(paramParcel, k);
      }
    }
    if (paramParcel.dataPosition() != i) {
      throw new zza.zza("Overread allowed size end=" + i, paramParcel);
    }
    return new IdToken(j, str2, str1);
  }
  
  public IdToken[] zzay(int paramInt)
  {
    return new IdToken[paramInt];
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/credentials/zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */