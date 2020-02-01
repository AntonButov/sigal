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
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

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

    private ImageView imageView;
    private TextView mname;
    private TextView mprice;
    private TextView mteTextView;
    FloatingActionButton fabfinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private int mParam2;
   // private
    private ArrayList<detailsat> detailsatsTV;
    private ArrayList<detailsat> detailsatsinter;

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
    public static satselect newInstance(String param1, int param2) {
        satselect fragment = new satselect();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_satselect, container, false);
        // Inflate the layout for this fragment
        imageView = view.findViewById(R.id.detail_image);
        mname = view.findViewById(R.id.textname);
        mprice = view.findViewById(R.id.textPrice);
        mteTextView = view.findViewById(R.id.textView4);
        fabfinder = view.findViewById(R.id.fab);
        fabfinder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:89632667744"));
                   v.getContext().startActivity(intent);
                }
            });
        detailsatsTV = new ArrayList();
        detailsatsTV.add(new detailsat(R.drawable.tricolorsmart,"Триколор двойной GS B532M (Сибирь)","7 500"+"₽","Комплект оборудования с цифровым приёмником GS B532M предназначен для просмотра спутниковых и онлайн каналов Триколор в формате стандартной и высокой (HD)(1) четкости. Приёмник оборудован двумя спутниковыми тюнерами, что позволяет просматривать телеканалы не только на одном телевизоре, но и на планшете, смартфоне, а также на втором телевизоре при условии подключения приёмника-клиента. Отличительной особенностью приёмника является встроенная память, объемом 8 ГБ. Для расширения возможностей телесмотрения рекомендуем подключить приёмник к сети Интернет и использовать внешний жёсткий диск объёмом от 64 ГБ, который Вы можете приобрести отдельно."));
        detailsatsTV.add(new detailsat(R.drawable.telekarta,"комплект спутникового ТВ Телекарта ( подписка на три года)","6 500"+"₽","HD-комплект спутникового телевидения «Телекарта» — это выбор прагматичных. При невысокой цене, оборудование «Телекарты» отвечает всем требованиям современного ТВ: доступ к сотням разнообразных каналов, высокая чёткость изображения, полностью русифицированное меню и электронная программа передач. Возможности спутникового приёмника “Телекарта” расширят ваше представление о телевидении, благодаря таким функциям, как управление эфиром (запись, перемотка и пауза), воспроизведение загруженных аудио, видеофайлов и изображений."));
        detailsatsTV.add(new detailsat(R.drawable.tricolorsmart,"Триколор с приёмниками GS B532M и C592 (Сибирь)","10 500"+"₽","Полностью готовое решение для просмотра спутниковых и онлайн каналов Триколор в формате стандартной и высокой (HD)(1) четкости на двух телевизорах. Цифровой приёмник-сервер GS B532M обеспечивает приём и трансляцию контента по локальной сети на приёмник-клиент GS C592, подключаемый ко второму телевизору, а также на планшет или смартфон. Отличительной особенностью приёмника-сервера является встроенная память, объемом 8 ГБ. В комплект входят антенна и конвертер для приёма спутникового сигнала. Для расширения возможностей телесмотрения рекомендуем подключить приёмник к сети Интернет и использовать внешний жёсткий диск объёмом от 64 ГБ, который Вы можете приобрести отдельно."));
        detailsatsTV.add(new detailsat(R.drawable.mts,"Спутниковое ТВ от МТС","3 600"+"₽","Даже в самых дальних уголках России\n" +
                "\n" +
                "Свыше 190 каналов, включая 35 в HD\n" +
                "Легко подключить где угодно: в загородном доме, на даче, в квартире\n" +
                "Функции управления эфиром: запись, пауза, перемотка\n" +
                "Интерактивные сервисы: видео по запросу, напоминания, телепрограмма, медиаплеер, родительский контроль, погода в вашем регионе, новости, пробки, курсы валют и многое другое\n" +
                "Комплектация:  \n" +
                "\n" +
                "1.цифровая приставка + карта доступа с подпиской на 1 мес. Если предоставите в обмен старый приемник то подписка будет 6 месяцев\n" +
                "\n" +
                "2. Тарелка 0.6 с конвертором"));
        detailsatsinter = new ArrayList<>();
        detailsatsinter.add(new detailsat(R.drawable.item_212,"Спутниковый интернет SenSat 2 Вт (Усиленный)","35 200"+"₽","Комплект спутникового оборудования НТ1100/0,74м/2 Вт в составе:\n" +
                "\n" +
                "модем спутниковый НТ1100,\n" +
                "\n" +
                "приёмопередатчик с встроенным LNB мощностью 2 Вт, антенна 0,74 м,\n" +
                "\n" +
                "опора настенная,\n" +
                "\n" +
                "кабель RG 6 (20м), разъём для кабеля RG 6 (2 шт.).\n" +
                "\n" +
                "Скорость до 45 мб/с Оптимальный вариант для северных широт и коллективного доступа."));
        detailsatsinter.add(new detailsat(R.drawable.ht11small1100,"Модем НТ1100 (JUPITER Hughes) Sensat","20 000"+"₽","НТ1100 (JUPITER Hughes) – высокопроизводительный широкополосный спутниковый маршрутизатор нового поколения с функцией передачи данных  в  многолучевых  спутниковых системах  Ка-диапазона со спутника АМ6\n" +
                "\n" +
                "Бесплатная доставка по России.\n" +
                "\n" +
                "Приглашаем участвовать в \"Партнёрской программе\""));
        detailsatsinter.add(new detailsat(R.drawable.internetpossmall,"Спутниковый интернет для посёлков","3 000"+"₽","Спутниковый интернет коллективного пользования для удалённых посёлков.\n" +
                "\n" +
                "Состав:\n" +
                "\n" +
                "1.МССС (малая станция спутниковой связи)\n" +
                "2. WI-FI передатчик с радиусом действия до 3км "+"\n"+"Данный комплект идеально подойдёт для деревень, вахтовых посёлков, нефтедобытчиков и др.\n" +
                "\n" +
                "Плюсы данного комплекта это же конечно в первую очередь-Цена, второй не маловажный плюс, каждый пользователь который подключился имеет свой собственный тариф, а значит и свой трафик. "));
        detailsatsinter.add(new detailsat(R.drawable.ht11small1100,"Модем НТ1100 (JUPITER Hughes) Радуга Интернет","6 000"+"₽","НТ1100 (JUPITER Hughes) – высокопроизводительный широкополосный спутниковый маршрутизатор нового поколения с функцией передачи данных  в  многолучевых  спутниковых системах  Ка-диапазона со спутника АМ6\n" +
                "\n" +
                "Бесплатная доставка по России."));
        detailsatsinter.add(new detailsat(R.drawable.vsat,"Спутниковый Модем MDM2200 ( NTC2299 )","10 000"+"₽","Основные характеристики   - Небольшой размер, настольное или настенное исполнение - DVB-S2 ACM в прямом канале - 4CPM MF-TDMA адаптивный обратный канал - Встроенное TCP ускорение и шифрование на 250 сессий - Многоуровневый QoS - Гибкая IP маршрутизация и адресация - Низкий уровень фазовых отклонений сигнала для приложений реального времени - Кэшируемый DNS и HTTP - Поддержка IPv4 и IPv6 Идеально подходит для тех кто желает уйти с KiteNet Стоимость с учётом доставки.\n" +
                "\n" +
                "Переде приобретением свяжитесь с поддержкой магазина"));
        detailsatsinter.add(new detailsat(R.drawable.gazprom,"Спутниковый интернет SAT3Play ПАО Газпром","41 000"+"₽","Основные характеристики товара:\n" +
                "\n" +
                "Скорость до 20 Мбит/с³\n" +
                "Способ оплаты Оплата через интернет с помощью банковской карты\n" +
                "Тип использования оконечного оборудования Комплект оборудования Sat3Play для спутникового интернета (производство Бельгия) \n" +
                "(по аналогии со спутниковым ТВ)\n" +
                "Обслуживание клиентов Дистанционно через личный кабинет и платежную систему Газпромбанка\n" +
                "География доступности Территория РФ (в зоне покрытия спутников: Ямал 401 и Ямал 402)\n" +
                "\n" +
                "1. Оборудование Sat3Play сертифицировано на территории Российской Федерации под названием малые земные станции спутниковой связи VSAT «Ямал-07К» Сертификат соответствия №ОС-2-СС-0536. Срок действия: с 24 декабря 2013 г. по 24 декабря 2016 г.\n" +
                "2. Указана максимальная скорость.\n" +
                "3. Услуги оказываются с использованием космического аппарата серии «Ямал», находящегося непосредственно в космическом пространстве, а также комплекса подготовительных и (или) вспомогательных (сопутствующих) наземных работ (услуг), технологически обусловленных (необходимых) и неразрывно связанных с оказанием услуг с использованием техники, находящейся непосредственно в космическом пространстве, и в соответствии с п.п. 5, п. 1 ст. 164 НК РФ облагаются НДС по ставке 0 %.\n" +
                "Тарифы для физ лиц http://gazpromcosmos.ru/home-and-garden/individual-access/rates-and-promotions/current-tariffs/\n" +
                "В отличие от KiteNet Гаспром гарантирует скорость хоть и спутник у них один. К сведению KiteNet арендует мощности у Газпрома.\n" +
                "В данный комплект входит бесплатный трафик на пол года"));
        switch (mParam1) {
            case "TV":
                imageView.setImageResource(detailsatsTV.get(mParam2).getImage());
                mname.setText(detailsatsTV.get(mParam2).getname());
                mprice.setText(detailsatsTV.get(mParam2).getprice());
                mteTextView.setText(detailsatsTV.get(mParam2).getdescription());
                break;
            case "internet":
                imageView.setImageResource(detailsatsinter.get(mParam2).getImage());
                mname.setText(detailsatsinter.get(mParam2).getname());
                mprice.setText(detailsatsinter.get(mParam2).getprice());
                mteTextView.setText(detailsatsinter.get(mParam2).getdescription());
                break;
        }
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
