package com.example.propertymanagementsystem.Service;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenAIService {

    // Get OpenAI API Key from application.properties
    @Value("${openai.api.key}")
    private String apiKey;


    // Generate AI report
    public String generateReport(String data) {

        // Create OpenAI client
        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .build();


        // Create prompt
        String prompt =

                "أنت محلل بيانات محترف ومتخصص في أنظمة إدارة الأملاك والعقارات.\n\n" +

                        "مهمتك إنشاء تقرير إداري احترافي ودقيق باللغة العربية " +
                        "اعتمادًا فقط على البيانات التي يتم تزويدك بها.\n\n" +


                        "========================\n" +
                        "قواعد البيانات والتحليل\n" +
                        "========================\n\n" +

                        "1. اكتب التقرير بالكامل باللغة العربية.\n" +
                        "2. اعتمد فقط على البيانات المقدمة لك.\n" +
                        "3. ممنوع اختراع أي رقم أو معلومة غير موجودة.\n" +
                        "4. ممنوع افتراض معلومات غير موجودة في البيانات.\n" +
                        "5. لا تعتبر عدد السجلات دليلًا على علاقة أو حالة معينة.\n" +
                        "6. لا تفترض أن عدد العقود يساوي عدد الوحدات أو المستأجرين.\n" +
                        "7. لا تفترض أن عدد جداول الدفع يساوي عدد العقود أو الوحدات.\n" +
                        "8. لا تحسب الإيرادات أو الأرباح أو حصة المالك أو عمولة المكتب " +
                        "إلا إذا كانت البيانات المطلوبة متوفرة.\n" +
                        "9. لا تحسب نسبة الإشغال إلا إذا توفرت بيانات إجمالي الوحدات " +
                        "وعدد الوحدات المشغولة.\n" +
                        "10. لا تحسب أي نسبة أو مؤشر مالي إذا لم تتوفر البيانات اللازمة.\n" +
                        "11. إذا كانت معلومة غير متوفرة، اكتب: غير متوفر في البيانات.\n" +
                        "12. حافظ على الأرقام كما وردت في البيانات.\n" +
                        "13. فرّق بين البيانات الفعلية والحسابات والتحليل.\n\n" +


                        "========================\n" +
                        "محتوى التقرير\n" +
                        "========================\n\n" +

                        "أنشئ التقرير بالترتيب التالي:\n\n" +

                        "1. عنوان التقرير\n" +
                        "2. تاريخ التقرير\n" +
                        "3. الملخص التنفيذي\n" +
                        "4. مؤشرات الأداء الرئيسية KPI\n" +
                        "5. نظرة عامة على المحفظة العقارية\n" +
                        "6. تحليل الوحدات\n" +
                        "7. تحليل العقود\n" +
                        "8. تحليل المدفوعات\n" +
                        "9. تحليل الاشتراكات\n" +
                        "10. الملاحظات والتحليلات\n" +
                        "11. البيانات غير المتوفرة\n" +
                        "12. التوصيات\n" +
                        "13. المخططات والمؤشرات البصرية\n\n" +


                        "========================\n" +
                        "المؤشرات KPI\n" +
                        "========================\n\n" +

                        "اعرض المؤشرات التي تتوفر بياناتها فقط، مثل:\n" +
                        "- عدد العقارات\n" +
                        "- عدد الوحدات\n" +
                        "- الوحدات المتاحة\n" +
                        "- الوحدات المشغولة\n" +
                        "- الوحدات تحت الصيانة\n" +
                        "- عدد المستأجرين\n" +
                        "- عدد العقود\n" +
                        "- العقود النشطة\n" +
                        "- العقود المنتهية\n" +
                        "- المدفوعات المسددة\n" +
                        "- المدفوعات المعلقة\n" +
                        "- المدفوعات المتأخرة\n" +
                        "- المبالغ المالية المتوفرة\n\n" +

                        "إذا لم تتوفر بيانات أحد المؤشرات، لا تخترع قيمته.\n\n" +


                        "========================\n" +
                        "المخططات\n" +
                        "========================\n\n" +

                        "إذا كانت البيانات كافية لإنشاء مخطط، أضف مخططًا بصريًا مناسبًا.\n" +

                        "استخدم مخططًا دائريًا لتوزيع الوحدات حسب الحالة.\n" +
                        "استخدم مخطط أعمدة لمقارنة حالات العقود أو المدفوعات.\n" +
                        "استخدم مخططًا خطيًا فقط عند توفر بيانات زمنية.\n\n" +

                        "لا تستخدم JavaScript.\n" +
                        "لا تستخدم مكتبات خارجية للمخططات.\n" +
                        "استخدم HTML وCSS فقط للمخططات البصرية البسيطة.\n" +
                        "لا تخترع بيانات للمخططات.\n\n" +


                        "========================\n" +
                        "HTML EMAIL\n" +
                        "========================\n\n" +

                        "أرجع التقرير بصيغة HTML فقط.\n" +
                        "لا تستخدم Markdown.\n" +
                        "لا تستخدم ```html.\n" +
                        "لا تضع أي نص خارج HTML.\n" +
                        "استخدم HTML وCSS مناسبين لرسائل البريد الإلكتروني.\n" +
                        "استخدم CSS Inline قدر الإمكان.\n" +
                        "استخدم direction: rtl.\n" +
                        "اجعل التقرير مناسبًا لـ Gmail وOutlook قدر الإمكان.\n" +
                        "استخدم جداول HTML للتنسيق.\n" +
                        "اجعل التصميم احترافيًا وواضحًا وسهل القراءة.\n" +
                        "استخدم بطاقات KPI بسيطة داخل جداول HTML.\n" +
                        "استخدم ألوانًا مهنية وهادئة.\n\n" +


                        "========================\n" +
                        "بيانات نظام إدارة الأملاك\n" +
                        "========================\n\n" +

                        data;


        // Create OpenAI request
        ResponseCreateParams params =
                ResponseCreateParams.builder()

                        // Send prompt to AI
                        .input(prompt)

                        // Use OpenAI model
                        .model(ChatModel.GPT_5_2)

                        .build();


        // Send request to OpenAI
        Response response =
                client.responses().create(params);


        // Get AI response
        return response.output()
                .stream()
                .flatMap(item -> item.message().stream())
                .flatMap(message -> message.content().stream())
                .flatMap(content -> content.outputText().stream())
                .map(outputText -> outputText.text())
                .reduce("", (a, b) -> a + b);
    }
}