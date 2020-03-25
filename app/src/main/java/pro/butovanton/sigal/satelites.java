package pro.butovanton.sigal;


import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class satelites extends Fragment {

   JSONPlaceHolderApi jsonPlaceHolderApi;
   NetworkService networkService;

    RecyclerView recyclerViewSat;
    satadapterinfo msatadapter;
    public static ArrayList<satelliteinfo> satelitteinfos;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.satelites, container, false);
        recyclerViewSat = view.findViewById(R.id.my_recycler_view);

        recyclerViewSat.addOnItemTouchListener(new RecyclerTouchListenerSat(this,recyclerViewSat, new RecyclerTouchListenerSat.ClickListener() {

            @Override
            public void onLongClick(View child, int childPosition) {

            }

            @Override
            public void onClick(View child, int childPosition) {

            }
        }));
            recyclerViewSat.setLayoutManager(new LinearLayoutManager(getContext()));
            satelitteinfos = new ArrayList<satelliteinfo>();
        satelitteinfos.add(new satelliteinfo("ЯМАЛ-601","ЯМАЛ-601 KA-ДИАПАЗОН, 49° в.д.","Территория предоставления услуг связи в Ka-диапазоне (спутник Ямал-601) - европейская часть России, включая Калининградскую область, Западную Сибирь, Иркутск и Красноярск. Согласно заявлению генерального директора ГКС Дмитрия Севастьянова, на спутнике “Ямал-601” планируется задействовать до 40 транспондеров Ка-диапазона.\n",49));
        satelitteinfos.add(new satelliteinfo("Экспресс АМ-6","Экспресс АМ-6 (Ka)/Ka  53° в.д.","На спутнике Express AM6 в позиции 53.0°E находится большое количество открытых каналов на русском языке. В центральной части России прием каналов со спутника возможен на тарелку размером 0.6 м. Некоторые каналы имеют часовые сдвиги, что создает определенные удобства при просмотре.В составе каналов спутника Express AM6 много региональных ТВ каналов и Российских радиоканалов.",53));
        satelitteinfos.add(new satelliteinfo("Ямал 402","Ямал 402, 55° в.д.","Российский спутник на борту которого только открытые каналы из России в разных часовых поясах такие как НТВ, Рен ТВ, ТНТ, Ю, Дисней и тд. Спутник вещает только в Ku диапазоне.",55));
        satelitteinfos.add(new satelliteinfo("ABS 1","ABS 1 /1A /1B, 75° в.д.","Популярная среди русскоговорящих зрителей спутниковая позиция. Много российских каналов в открытом виде, а также каналы платного российского оператора \"МТС-ТВ\". Зона покрытия почти вся территория Евразии. Вещает только в Ku диапазоне.",75));
        satelitteinfos.add(new satelliteinfo("Horizons-2","Horizons-2 / IS-15, 85.2° в.д.","Данный спутник транслирует в основном каналы платного российского пакета \"Континент ТВ\" и \"Телекарта ТВ\" и почти 20 российских каналов в открытом виде. Вещает только в Ku диапазоне в форматах MPEG4/MPEG2. Так же на данном спутнике присутствуют каналы \"СТС медиа\" в условной кодировке \"BISS\".",85));
        satelitteinfos.add(new satelliteinfo("ЯМАЛ 401","ЯМАЛ 401, 90° в.д.","Один и популярных российских спутников который вещает довольно хороший пакет российских федеральных телеканалов в открытом виде (ТНТ, Перец, РЕН-ТВ, Домашний, Россия2, Россия24, НТВ, Звезда, ТВ3, СТС и тд...). Несколько каналов из Туркмении и Казахстана. Охватывает почти всю территорию России и страны СНГ. Диапазоны С и Ku.",90));
        satelitteinfos.add(new satelliteinfo("Экспресс АМ-5","Экспресс AM-5 (Ka) 140° в.д.","На этой позиции стоит спутник предназначений для покрытия дальнего востока России. Имеет Ku и C диапазоны. Идет вещание федеральных и региональных российских каналов, а также платные пакеты VIVA - Восточный Экспресс и канал Наш Футбол.",140));
        msatadapter = new satadapterinfo(satelitteinfos);
        recyclerViewSat.setAdapter(msatadapter);

// Load CAs from an InputStream
// (could be from a resource or ByteArrayInputStream or ...)
   /*     CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }
// From https://www.washington.edu/itconnect/security/ca/load-der.crt
        InputStream caInput = null;
        try {
            caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Certificate ca = null;
        try {
            ca = cf.generateCertificate(caInput);
            System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
        } catch (CertificateException e) {
            e.printStackTrace();
        } finally {
            try {
                caInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

// Create a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = null;
        try {
            keyStore = KeyStore.getInstance(keyStoreType);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        try {
            keyStore.load(null, null);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            keyStore.setCertificateEntry("ca", ca);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

// Create a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = null;
        try {
            tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            tmf.init(keyStore);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

// Create an SSLContext that uses our TrustManager
        SSLContext context = null;
        try {
            context = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            context.init(null, tmf.getTrustManagers(), null);
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

// Tell the URLConnection to use a SocketFactory from our SSLContext
        URL url = null;
        try {
            url = new URL("https://certs.cac.washington.edu/CAtest/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpsURLConnection urlConnection =
                null;
        try {
            urlConnection = (HttpsURLConnection)url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        urlConnection.setSSLSocketFactory(context.getSocketFactory());
        InputStream in = null;
        try {
            in = urlConnection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //copyInputStreamToOutputStream(in, System.out);*/


        networkService = NetworkService.getInstance();
        jsonPlaceHolderApi = networkService.getJSONApi();
            jsonPlaceHolderApi.getAllPosts().enqueue(new Callback<JsonArray>() {
                @Override
                public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                    Log.d("DEBUG", "respons ok");
                }

                @Override
                public void onFailure(Call<JsonArray> call, Throwable t) {
                    Log.d("DEBUG", t.toString());

                }
            });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
     //   Log.d("DEBUG", "Resume");
        msatadapter.notifyDataSetChanged();
    }
}

