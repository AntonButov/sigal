package pro.butovanton.sigal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static java.lang.Math.atan;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.tan;
import static java.lang.Math.toDegrees;
import static java.lang.Math.toRadians;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link satselect.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link satselect#newInstance} factory method to
 * create an instance of this fragment.
 */
public class satselect extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    TextView mteTextView;
    FloatingActionButton fabfinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public satselect() {
        // Required empty public constructor

    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment satselect.
     */
    // TODO: Rename and change types and number of parameters
    public static satselect newInstance(String param1, String param2) {
        satselect fragment = new satselect();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_satselect, container, false);
        // Inflate the layout for this fragment
        mteTextView = view.findViewById(R.id.textView4);
        fabfinder = view.findViewById(R.id.fabfinder);
        fabfinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), camera.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        if (mteTextView != null)
        mteTextView.setText("Полностью готовое решение для просмотра спутниковых и онлайн каналов Триколор в формате стандартной и высокой (HD)(1) четкости на двух телевизорах. Цифровой приёмник-сервер GS B532M обеспечивает приём и трансляцию контента по локальной сети на приёмник-клиент GS C592, подключаемый ко второму телевизору, а также на планшет или смартфон. Отличительной особенностью приёмника-сервера является встроенная память, объемом 8 ГБ. В комплект входят антенна и конвертер для приёма спутникового сигнала. Для расширения возможностей телесмотрения рекомендуем подключить приёмник к сети Интернет и использовать внешний жёсткий диск объёмом от 64 ГБ, который Вы можете приобрести отдельно. Сервисы Триколор Абонентам Триколор предоставляется широкий выбор каналов и удобные интерактивные сервисы  Более 150 каналов. Десятки — в HD-качестве. «Онлайн ТВ» - просмотр программы с начала, перемотка и пауза без использования жесткого диска и архив передач до 7 дней. «Управляй эфиром» - функции паузы, перемотки и записи. «Кинозалы» - новинки кино в Вашем приёмнике без рекламы и расписания. «Лучшее на ТВ» - подборки лучшего контента в удобных жанровых списках.");
       // float F = conerplace(37,56,36);//=26гр
       // float A = azimuthsat(37,56,36);//=181гр
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private float conerplace(float g2, float v, float g1) {
        g2 = (float) toRadians(g2);
        g1 = (float) toRadians(g1);
        v = (float) toRadians(v);
        return (float) toDegrees(Math.atan((cos(g2-g1)*cos(v)-0.151)/sqrt(1-pow(cos(g2-g1),2)*pow(cos(v),2))));
    }

    private float azimuthsat(float g2, float v, float g1) {
        g2 = (float) toRadians(g2);
        g1 = (float) toRadians(g1);
        v = (float) toRadians(v);
        return (float) (180 + toDegrees(atan(tan(g2-g1)/sin(v))));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
