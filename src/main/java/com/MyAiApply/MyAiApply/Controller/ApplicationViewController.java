package com.MyAiApply.MyAiApply.Controller;

import com.MyAiApply.MyAiApply.Service.OpenAiServiceWrapper;
import com.MyAiApply.MyAiApply.model.Application;
import com.MyAiApply.MyAiApply.repository.ApplicationRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/applications")
public class ApplicationViewController {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private OpenAiServiceWrapper openAiServiceWrapper;

    // Отображение списка заявок
    @GetMapping("/list")
    public String listApplications(Model model) {
        List<Application> applications = applicationRepository.findAll();
        model.addAttribute("applications", applications);
        return "applications";
    }

    // Отображение формы для добавления новой заявки
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("application", new Application());
        return "add-application";
    }

    // Обработка добавления новой заявки
    @PostMapping("/add")
    public String addApplication(@Valid @ModelAttribute Application application, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Validation failed");
            return "redirect:/applications/add";
        }
        applicationRepository.save(application);
        redirectAttributes.addFlashAttribute("message", "Заявка успешно добавлена!");
        return "redirect:/applications/list";
    }

    // Удаление заявки
    @PostMapping("/delete/{id}")
    public String deleteApplication(@PathVariable Long id) {
        applicationRepository.deleteById(id);
        return "redirect:/applications/list";
    }

    // Отображение формы для редактирования заявки
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid application ID: " + id));
        model.addAttribute("application", application);
        return "edit-application";
    }

    // Обработка редактирования заявки
    @PostMapping("/edit/{id}")
    public String updateApplication(@PathVariable Long id, @ModelAttribute Application application) {
        application.setId(id);
        applicationRepository.save(application);
        return "redirect:/applications/list";
    }

    // Генерация сопроводительного письма с использованием ИИ
    @GetMapping("/generate-cover-letter/{id}")
    public String generateCoverLetter(@PathVariable Long id, Model model) {
        Application application = applicationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid application ID: " + id));

        // Генерация сопроводительного письма
        String coverLetter = openAiServiceWrapper.generateCoverLetter(application.getJobTitle(), application.getCompany(), "Ваше имя");
        model.addAttribute("coverLetter", coverLetter);
        model.addAttribute("application", application);

        return "cover-letter";
    }
}