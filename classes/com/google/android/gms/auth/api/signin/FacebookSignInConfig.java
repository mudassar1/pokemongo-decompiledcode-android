package com.google.android.gms.auth.api.signin;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Collections;

public class FacebookSignInConfig
  implements SafeParcelable
{
  public static final Parcelable.Creator<FacebookSignInConfig> CREATOR = new zzb();
  private Intent mIntent;
  final int versionCode;
  private final ArrayList<String> zzSX;
  
  public FacebookSignInConfig()
  {
    this(1, null, new ArrayList());
  }
  
  FacebookSignInConfig(int paramInt, Intent paramIntent, ArrayList<String> paramArrayList)
  {
    this.versionCode = paramInt;
    this.mIntent = paramIntent;
    this.zzSX = paramArrayList;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = false;
    if (paramObject == null) {}
    for (;;)
    {
      return bool1;
      try
      {
        FacebookSignInConfig localFacebookSignInConfig = (FacebookSignInConfig)paramObject;
        if (this.zzSX.size() == localFacebookSignInConfig.zzlS().size())
        {
          boolean bool2 = this.zzSX.containsAll(localFacebookSignInConfig.zzlS());
          if (bool2) {
            bool1 = true;
          }
        }
      }
      catch (ClassCastException localClassCastException) {}
    }
  }
  
  public int hashCode()
  {
    Collections.sort(this.zzSX);
    return this.zzSX.hashCode();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public Intent zzlR()
  {
    return this.mIntent;
  }
  
  public ArrayList<String> zzlS()
  {
    return new ArrayList(this.zzSX);
  }
}


/* Location:              /Users/tjledwith/Downloads/dex2jar-0.0.9.8/classes_dex2jar.jar!/com/google/android/gms/auth/api/signin/FacebookSignInConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */